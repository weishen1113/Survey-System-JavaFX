/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.survey.surveysystem;

import com.survey.surveysystem.auth.User;
import com.survey.surveysystem.file.ReadFile;
import com.survey.surveysystem.models.SurveyModel;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author a
 */
public class SurveyListViewController implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private TableView<SurveyModel> grid1;
    @FXML
    private TableColumn<SurveyModel, String> colSCid;
    @FXML
    private TableColumn<SurveyModel, String> colCreatorName;
    @FXML
    private TableColumn<SurveyModel, String> colSurveyID;
    @FXML
    private TableColumn<SurveyModel, String> colTitle;
    @FXML
    private TableColumn<SurveyModel, String> colResponse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCreatorName.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getCreatorName()));
        colSCid.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getScID()));
        colSurveyID.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getSurveyID()));
        colTitle.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getSurveyTitle()));
        colResponse.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(Long.toString(clbck.getValue().getResponses())));
        grid1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                try {
                    String surveyID = newSelection.getSurveyID();
                    if (!surveyID.isEmpty()) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("surveyView.fxml"));
                        Parent root = loader.load();
                        SurveyViewController destinationController = loader.getController();
                        destinationController.setSurveyId(surveyID);
                        destinationController.setSurveyName(newSelection.getSurveyTitle());
                        destinationController.setBackPage("surveyListView");
                        destinationController.start();
                        App.getScene().setRoot(root);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        loadDataToGrid();
    }

    @FXML
    private void onBack(ActionEvent event) {
        try {
            App.setRoot("clientLoginView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadDataToGrid() {
        ObservableList<SurveyModel> data = FXCollections.observableArrayList();
        ReadFile readFile = new ReadFile("surveys.txt");
        for (String line : readFile.ReadFileByLines()) {
            if (line.contains(";")) {
                String[] s = line.split(";");
                if (s.length == 4) {
                    long count = 0;
                    data.add(new SurveyModel(s[0], s[1], s[2], s[3], count));
                }
            }
        }
        grid1.setItems(data);
    }

}
