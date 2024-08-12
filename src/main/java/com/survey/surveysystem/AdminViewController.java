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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author a
 */
public class AdminViewController implements Initializable {

    @FXML
    private Button btnCreator;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void logout(ActionEvent event) {
        try {
            App.setRoot("loginView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void createSurveyCreator(ActionEvent event) {
        try {
            App.setRoot("surveyCreator");
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
    void onManageSurveyClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SurveyManageView.fxml"));
            Parent root = loader.load();
            SurveyManageViewController destinationController = loader.getController();
            destinationController.setBackLink("adminView");
            App.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void onSurveyViewClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("surveySearch.fxml"));
            Parent root = loader.load();
            SurveySearchController destinationController = loader.getController();
            destinationController.setBackPage("adminView");
            App.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
        @FXML
    void onViewResponseClick(ActionEvent event) {
        try {
            App.setRoot("viewResponses");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void onAboutClick(ActionEvent event) {
        try {
            App.setRoot("adminAboutView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
