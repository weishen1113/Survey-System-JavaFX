/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.survey.surveysystem;

import com.survey.surveysystem.file.ReadFile;
import com.survey.surveysystem.file.WriteFile;
import com.survey.surveysystem.models.SurveyCreatorModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author a
 */
public class SurveyCreatorController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private RadioButton checkFemale;

    @FXML
    private RadioButton checkMale;

    @FXML
    private TableColumn<SurveyCreatorModel, String> colEmailAddress;

    @FXML
    private TableColumn<SurveyCreatorModel, String> colFaculity;

    @FXML
    private TableColumn<SurveyCreatorModel, String> colFirstName;

    @FXML
    private TableColumn<SurveyCreatorModel, String> colGender;

    @FXML
    private TableColumn<SurveyCreatorModel, String> colLastName;

    @FXML
    private TableColumn<SurveyCreatorModel, String> colPhone;

    @FXML
    private TableColumn<SurveyCreatorModel, String> colScId;

    @FXML
    private TableView<SurveyCreatorModel> grid1;

    @FXML
    private TextField txtEmail;

    @FXML
    private ComboBox<String> txtFaculty;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtSCid;

    @FXML
    private TextField txtUsername;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> faculties = FXCollections.observableArrayList();
        faculties.addAll("Computer Science", "Engineering", "Business", "Medicine");
        txtFaculty.setItems(faculties);
        colScId.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getScId()));
        colFirstName.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getFirstName()));
        colLastName.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getLastName()));
        colFaculity.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getFaculity()));
        colEmailAddress.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getEmail()));
        colPhone.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getPhone()));
        colGender.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getGender()));
        loadDataToGrid();
        grid1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtFirstName.setText(newSelection.getFirstName());
                txtLastName.setText(newSelection.getLastName());
                txtEmail.setText(newSelection.getEmail());
                txtPhone.setText(newSelection.getPhone());
                txtSCid.setText(newSelection.getScId());
                txtFaculty.setValue(newSelection.getFaculity());
                if (newSelection.getFaculity().equals("Male")) {
                    checkMale.setSelected(true);
                }
                if (newSelection.getGender().equals("Female")) {
                    checkFemale.setSelected(true);
                }
                ReadFile readFile = new ReadFile("users.txt");
                for (String line : readFile.ReadFileByLines()) {
                    if (line.contains(";")) {
                        String[] s = line.split(";");
                        if (s[0].equals(newSelection.getScId())) {
                            txtUsername.setText(s[1]);
                            txtPassword.setText(s[2]);
                            txtUsername.setDisable(true);
                        }
                    }
                }
            }
        });
    }

    @FXML
void addCreator(ActionEvent event) {
    if (validateInputs()) {
        WriteFile writeFile = new WriteFile("creators.txt");
        writeFile.writeFile(getDataString());
        WriteFile writeFile1 = new WriteFile("users.txt");
        writeFile1.writeFile(txtSCid.getText().concat(";").concat(txtUsername.getText())
                .concat(";").concat(txtPassword.getText()).concat(";").concat("creator"));
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Survey Creator is Registered.");
        alert.show();
        clear();
        loadDataToGrid();
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill in all the required fields.");
        alert.show();
    }
}

    @FXML
void deleteCreator(ActionEvent event) {
    if (txtSCid.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter Survey Creator ID.");
        alert.show();
    } else {
        WriteFile writeFile = new WriteFile("creators.txt");
        writeFile.updateFile(txtSCid.getText(), "", 0);
        clear();
        loadDataToGrid();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Survey Creator is deleted.");
        alert.show();
    }
}

    @FXML
void updateCreator(ActionEvent event) {
    if (validateInputs()) {
        WriteFile writeFile = new WriteFile("creators.txt");
        writeFile.updateFile(txtSCid.getText(), getDataString(), 0);
        WriteFile writeUserFile = new WriteFile("users.txt");
        String userDataString = (txtSCid.getText().concat(";").concat(txtUsername.getText())
                .concat(";").concat(txtPassword.getText()).concat(";").concat("creator"));
        writeUserFile.updateFile(txtSCid.getText(), userDataString, 0);
        clear();
        loadDataToGrid();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Survey Creator is updated.");
        alert.show();
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill in all the required fields.");
        alert.show();
    }
}

    private String getDataString() {
        String line = txtSCid.getText().concat(";") + txtFirstName.getText().concat(";") + txtLastName.getText()
                .concat(";") + txtEmail.getText().concat(";") + txtPhone.getText()
                .concat(";") + txtFaculty.getValue()
                .concat(";").concat(checkFemale.isSelected() == true ? "Female" : "Male");
        return line;
    }

    private void clear() {
        txtUsername.clear();
        txtPassword.clear();
        txtEmail.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtPhone.clear();
        txtSCid.clear();
        checkFemale.setSelected(false);
        checkMale.setSelected(false);
        txtUsername.setDisable(false);
    }

    private void loadDataToGrid() {
        ObservableList<SurveyCreatorModel> data = FXCollections.observableArrayList();
        ReadFile readFile = new ReadFile("creators.txt");
        for (String line : readFile.ReadFileByLines()) {
            if (line.contains(";")) {
                String[] s = line.split(";");
                if (s.length == 7) {
                    data.add(new SurveyCreatorModel(s[0], s[1], s[2], s[5], s[3], s[4], s[6]));
                }
            }
        }
        grid1.setItems(data);
    }

    @FXML
    void onFemaleClick(ActionEvent event) {
        checkMale.setSelected(false);
    }

    @FXML
    void onMaleClick(ActionEvent event) {
        checkFemale.setSelected(false);
    }

    @FXML
    void onBackClick(ActionEvent event) {
        try {
            App.setRoot("adminView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void onShowPassword(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, txtPassword.getText());
        alert.show();
    }

    private boolean validateInputs() {
    if (txtSCid.getText().isEmpty() || txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty()
            || txtEmail.getText().isEmpty() || txtPhone.getText().isEmpty() || txtFaculty.getValue() == null
            || (!checkFemale.isSelected() && !checkMale.isSelected())) {
        return false;
    }
    return true;
}
}
