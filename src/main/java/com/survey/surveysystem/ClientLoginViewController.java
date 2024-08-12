/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.survey.surveysystem;

import com.survey.surveysystem.models.ResponseModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author a
 */
public class ClientLoginViewController implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private RadioButton checkFemale;

    @FXML
    private RadioButton checkMale;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    void onFemaleCheck(ActionEvent event) {
        checkMale.setSelected(false);
    }

    @FXML
    void onMaleCheck(ActionEvent event) {
        checkFemale.setSelected(false);
    }

    @FXML
    void login(ActionEvent event) {
        if (!txtFirstName.getText().isEmpty() && !txtLastName.getText().isEmpty()) {
            try {
                saveClient();
                App.setRoot("surveyListView");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill the form");
            alert.show();
        }
    }

    @FXML
    void onAdminLogin(ActionEvent event) {
        try {
            App.setRoot("loginView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void saveClient(){
        ResponseModel response = new ResponseModel();
        response.setFirstName(txtFirstName.getText());
        response.setLastName(txtLastName.getText());
        response.setGender(checkFemale.isSelected() == true ? "Female" : "Male");
        
    }
}
