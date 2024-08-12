/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.survey.surveysystem;

import com.survey.surveysystem.file.ReadFile;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author a
 */
public class SurveySearchController implements Initializable {
    private String backPage;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField txtSurveyId;

    private String surveyTitle;

    @FXML
    void onSearch(ActionEvent event) {
        try {
            if (isSurveyIdExist(txtSurveyId.getText())) {
                String surveyID = txtSurveyId.getText();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("searchView.fxml"));
                Parent root = loader.load();
                SurveyViewController destinationController = loader.getController();
                destinationController.setSurveyId(surveyID);
                destinationController.setSurveyName(surveyTitle);
                destinationController.setBackPage("surveySearch");
                destinationController.start();
                App.getScene().setRoot(root);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Survey does not exist.");
                alert.show();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private boolean isSurveyIdExist(String id) {
        ReadFile readFile = new ReadFile("surveys.txt");
        for (String line : readFile.ReadFileByLines()) {
            String[] s = line.split(";");
            if (s.length >= 4) {
                if (s[2].equals(id)) {
                    surveyTitle = s[3];
                    return true;
                }
            }
        }
        return false;
    }

    @FXML
    void onBackClick(ActionEvent event) {
        try {
            if(App.getUserType().equals("surveyCreator"))
            {
             String backPageLink = "surveyCreatorView";
            if(backPage != null){
                backPageLink = backPage;
            }
            App.setRoot(backPageLink);
            }
            else
            {
            String backPageLink = "adminView";
            if(backPage != null){
                backPageLink = backPage;
            }
            App.setRoot(backPageLink);
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setBackPage(String backPage) {
       this.backPage = backPage;
    }

}
