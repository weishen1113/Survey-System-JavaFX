/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.survey.surveysystem;

import com.survey.surveysystem.auth.User;
import com.survey.surveysystem.file.ReadFile;
import com.survey.surveysystem.file.WriteFile;
import com.survey.surveysystem.models.SurveyCreatorModel;
import com.survey.surveysystem.models.SurveyModel;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author a
 */
public class SurveyManageViewController implements Initializable {

    private String backLink;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<SurveyModel, String> colCreatorName;

    @FXML
    private TableColumn<SurveyModel, String> colSCid;

    @FXML
    private TableColumn<SurveyModel, String> colSurveyID;

    @FXML
    private TableColumn<SurveyModel, String> colTitle;

    @FXML
    private TableColumn<SurveyModel, String> colResponse;

    @FXML
    private TextField txtCreatorName;

    @FXML
    private TextField txtSCid;

    @FXML
    private TextField txtSurveyID;

    @FXML
    private TextField txtSurveyTitle;
    @FXML
    private TableView<SurveyModel> grid1;
    @FXML
    private Button btnBack;

    private String scID = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtSCid.setText(User.loggedInUser);
        if (User.userName.equals("admin")) {
            txtCreatorName.setText(User.userName);
            btnAdd.setDisable(true);
        } else {
            colResponse.setVisible(false);
            txtCreatorName.setText(getCreatorName());
        }
        colCreatorName.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getCreatorName()));
        colSCid.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getScID()));
        colSurveyID.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getSurveyID()));
        colTitle.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getSurveyTitle()));
        colResponse.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(Long.toString(clbck.getValue().getResponses())));
        grid1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // txtCreatorName.setText(newSelection.getCreatorName());
                scID = newSelection.getScID();
                txtSurveyID.setText(newSelection.getSurveyID());
                txtSurveyTitle.setText(newSelection.getSurveyTitle());
            }
        });
        loadDataToGrid();
    }

    @FXML
void add(ActionEvent event) {
    if (validateInputs()) {
        WriteFile writeFile = new WriteFile("surveys.txt");
        writeFile.writeFile(getDataString());
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Added.");
        alert.show();
        loadDataToGrid();
        clear();
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill in all the required fields.");
        alert.show();
    }
}

@FXML
void delete(ActionEvent event) {
    if (!txtSurveyID.getText().isEmpty() && !scID.isEmpty()) {
        WriteFile writeFile = new WriteFile("surveys.txt");
        writeFile.updateFileWithTwoKeys(txtSurveyID.getText(), scID, "", 2, 0);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted.");
        alert.show();
        loadDataToGrid();
        clear();
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a survey to delete.");
        alert.show();
    }
}

@FXML
void update(ActionEvent event) {
    if (validateInputs()) {
        WriteFile writeFile = new WriteFile("surveys.txt");
        writeFile.updateFileWithTwoKeys(txtSurveyID.getText(), scID, getDataString(), 2, 0);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Updated.");
        alert.show();
        loadDataToGrid();
        clear();
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill in all the required fields.");
        alert.show();
    }
}

    @FXML
    void onBack(ActionEvent event) {
        try {
            String backView = "surveyCreatorView";
            if (backLink != null) {
                backView = backLink;
            }
            App.setRoot(backView);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private String getDataString() {
        return txtSCid.getText().concat(";").concat(txtCreatorName.getText()).concat(";")
                .concat(txtSurveyID.getText()).concat(";").concat(txtSurveyTitle.getText());
    }

    private void clear() {
        //txtCreatorName.clear();
        //txtSCid.clear();
        txtSurveyID.clear();
        txtSurveyTitle.clear();
        scID = "";
    }

    private void loadDataToGrid() {
        ObservableList<SurveyModel> data = FXCollections.observableArrayList();
        ReadFile readFile = new ReadFile("surveys.txt");
        Map<String, Long> countResponses = getCountSurveyResponses();
        for (String line : readFile.ReadFileByLines()) {
            if (line.contains(";")) {
                String[] s = line.split(";");
                if (s.length == 4) {
                    long count = countResponses.getOrDefault(s[2], Long.parseLong("0"));
                    if (User.userName.equals("admin")) {
                        data.add(new SurveyModel(s[0], s[1], s[2], s[3],count));
                    } else {
                        if (User.loggedInUser.equals(s[0])) {
                            data.add(new SurveyModel(s[0], s[1], s[2], s[3], 0));
                        }
                    }
                }
            }
        }
        grid1.setItems(data);
    }

    private String getCreatorName() {
        String name = "";
        ReadFile readFile = new ReadFile("creators.txt");
        for (String line : readFile.ReadFileByLines()) {
            if (line.contains(";")) {
                String[] s = line.split(";");
                if (s.length > 2) {
                    if (s[0].equals(User.loggedInUser)) {
                        name = s[1];
                    }
                }
            }
        }
        return name;
    }

    public void setBackLink(String backLink) {
        this.backLink = backLink;
    }
    
    private Map<String, Long> getCountSurveyResponses(){
        ReadFile readFile = new ReadFile("responses.txt");
        return readFile.ReadFileByLines().stream()
                .filter(response -> response.contains(";"))
                .map(response -> response.split(";")[0])
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
    }

    private boolean validateInputs() {
    if (txtSCid.getText().isEmpty() || txtCreatorName.getText().isEmpty() || txtSurveyID.getText().isEmpty()
            || txtSurveyTitle.getText().isEmpty()) {
        return false;
    }
    return true;
}
}
