/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.survey.surveysystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author a
 */
public class SurveyCreatorViewController implements Initializable {

    @FXML
    private Button btnManageSurvey;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void onManageSurveyClick(ActionEvent event) {
        try {
            App.setRoot("SurveyManageView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void onManageQuestionClick(ActionEvent event) {
        try {
            App.setRoot("questionManager");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void onSurveyViewClick(ActionEvent event) {
        try {
            App.setRoot("surveySearch");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void onMenuLogout(ActionEvent event) {
        try {
            App.setRoot("scLoginView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void onAboutClick(ActionEvent event) {
        try {
            App.setRoot("scAboutView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
