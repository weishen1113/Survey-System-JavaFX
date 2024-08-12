/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.survey.surveysystem;

import com.survey.surveysystem.file.ReadFile;
import com.survey.surveysystem.file.WriteFile;
import com.survey.surveysystem.models.QuestionModel;
import com.survey.surveysystem.models.SurveyCreatorModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author a
 */
public class QuestionManagerController implements Initializable {

    @FXML
    private TableColumn<QuestionModel, String> ColQPosition;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<QuestionModel, String> colQId;

    @FXML
    private TableColumn<QuestionModel, String> colSurveyID;

    @FXML
    private TableColumn<QuestionModel, String> colType;

    @FXML
    private TableColumn<QuestionModel, String> colqText;

    @FXML
    private ComboBox<String> combType;

    @FXML
    private TableView<QuestionModel> grid1;
    
    @FXML
    private Label infoLabel1;
    
    @FXML
    private Label infoLabel2;
    @FXML
    private Label infoLabel3;
    @FXML
    private Label infoLabel4;
    
    @FXML
    private TextField txtQAns1;

    @FXML
    private TextField txtQAns2;

    @FXML
    private TextField txtQAns3;

    @FXML
    private TextField txtQAns4;

    @FXML
    private TableColumn<QuestionModel, String> colAns;

    @FXML
    private TextArea txtQAns;

    @FXML
    private TextField txtQId;

    @FXML
    private TextField txtQPosition;

    @FXML
    private TextArea txtQText;

    @FXML
    private TextField txtSurveyID;

    @FXML
    void onAdd(ActionEvent event) {
        if (txtQPosition.getText().matches("[0-9]+") && combType.getValue() != null) {
            WriteFile writeFile = new WriteFile("questions.txt");
            writeFile.writeFile(getDataString());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Added");
            alert.show();
            clear();
            loadDataToGrid();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Postion takes only numbers");
            alert.show();
        }
    }

    @FXML
    void onDelete(ActionEvent event) {
        if (txtQPosition.getText().matches("[0-9]+") && combType.getValue() != null) {
            WriteFile writeFile = new WriteFile("questions.txt");
            writeFile.updateFile(txtQId.getText(), "", 0);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted");
            alert.show();
            clear();
            loadDataToGrid();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Postion takes only numbers");
            alert.show();
        }
    }

    @FXML
    void onUpdate(ActionEvent event) {
        if (txtQPosition.getText().matches("[0-9]+") && combType.getValue() != null) {
            WriteFile writeFile = new WriteFile("questions.txt");
            writeFile.updateFile(txtQId.getText(), getDataString(), 0);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Updated");
            alert.show();
            clear();
            loadDataToGrid();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Postion takes only numbers");
            alert.show();
        }
    }

    @FXML
    void onBack(ActionEvent event) {
        try {
            App.setRoot("surveyCreatorView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> types = FXCollections.observableArrayList();
        types.addAll("MCQ", "PolarQuestion", "Open-ended");
        combType.setItems(types);
        ColQPosition.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(Integer.toString(clbck.getValue().getPostion())));
        colQId.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getId()));
        colSurveyID.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getSurveyId()));
        colType.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getType()));
        colqText.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getText()));
        //colAns.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getAnswer()));

        grid1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                //txtQAns.setText(newSelection.getAnswer());
                txtQId.setText(newSelection.getId());
                txtQPosition.setText(Integer.toString(newSelection.getPostion()));
                txtQText.setText(newSelection.getText());
                txtSurveyID.setText(newSelection.getSurveyId());
                combType.setValue(newSelection.getType());
                if ("MCQ".equals(combType.getValue())) {
                    String getOptionsData = newSelection.getMCQOptions();
                    String[] options = getOptionsData.split("#");
                    txtQAns1.setText(options[0]);
                    txtQAns2.setText(options[1]);
                    txtQAns3.setText(options[2]);
                    txtQAns4.setText(options[3]);
                }
            }
        });
        loadDataToGrid();
    }
    @FXML
    void onMCQ(ActionEvent event) {

        if ("MCQ".equals(combType.getValue())) {
            infoLabel1.setText("Option 1");
            infoLabel2.setText("Option 2");
            infoLabel3.setText("Option 3");
            infoLabel4.setText("Option 4");
            txtQAns1.setVisible(true);
            txtQAns2.setVisible(true);
            txtQAns3.setVisible(true);
            txtQAns4.setVisible(true);
            
        } else {
            infoLabel1.setText("");
            infoLabel2.setText("");
            infoLabel3.setText("");
            infoLabel4.setText("");
            txtQAns1.setVisible(false);
            txtQAns2.setVisible(false);
            txtQAns3.setVisible(false);
            txtQAns4.setVisible(false);
        }
    }
    public String getTxtQAns1() {
        return txtQAns1.getText();
    }

    public String getTxtQAns2() {
        return txtQAns2.getText();
    }

    public String getTxtQAns3() {
        return txtQAns3.getText();
    }

    public String getTxtQAns4() {
        return txtQAns4.getText();
    }

    private String getDataString() {
        String dataString = txtQId.getText().concat(";").concat(txtQPosition.getText()).concat(";")
                .concat(txtSurveyID.getText()).concat(";").concat(txtQText.getText())
                .concat(";").concat(combType.getValue());
        if ("MCQ".equals(combType.getValue())) {
            dataString += ";" + txtQAns1.getText() + ";" + txtQAns2.getText() + ";"
            + txtQAns3.getText() + ";" + txtQAns4.getText();
        } else {
        // Use placeholders for non-MCQ case
                dataString += ";;;;";
        }
                return dataString;
        }
        

    private void clear() {
        txtQAns.clear();
        txtQId.clear();
        txtQPosition.clear();
        txtQText.clear();
        txtSurveyID.clear();
    }

    private void loadDataToGrid() {
        ObservableList<QuestionModel> data = FXCollections.observableArrayList();
        ReadFile readFile = new ReadFile("questions.txt");
        for (String line : readFile.ReadFileByLines()) {
            if (line.contains(";")) {
                String[] s = line.split(";");
                if (s.length == 5 ) {
                    data.add(new QuestionModel(s[0], Integer.parseInt(s[1]), s[2], s[3], s[4]));
                }
                else if(s.length == 9){
                    data.add(new QuestionModel(s[0], Integer.parseInt(s[1]), s[2], s[3], s[4], s[5],s[6],s[7],s[8]));
                }
            }
        }
        grid1.setItems(data);
    }

}
