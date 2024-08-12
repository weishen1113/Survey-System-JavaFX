/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.survey.surveysystem;

/**
 *
 * @author User
 */
import com.survey.surveysystem.file.ReadFile;
import com.survey.surveysystem.models.viewResponseModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class viewResponseController implements Initializable{


    @FXML
    private TableView<viewResponseModel> grid1;

    @FXML
    private TableColumn<viewResponseModel, String> FirstNameID;

    @FXML
    private TableColumn<viewResponseModel, String> LastNameID;

    @FXML
    private TableColumn<viewResponseModel, String> SurveyID;

    private void initializeColumns() {
        FirstNameID.setCellValueFactory(new PropertyValueFactory<>("Fname"));
        LastNameID.setCellValueFactory(new PropertyValueFactory<>("Lname"));
        SurveyID.setCellValueFactory(new PropertyValueFactory<>("SurvID"));
    }

    private void loadDataToGrid() {
        ObservableList<viewResponseModel> data = FXCollections.observableArrayList();

        ReadFile readFile = new ReadFile("respo.txt");

        // Define the regex pattern to capture the text before "!!"
        Pattern pattern = Pattern.compile("^(.*?)(?:!!)");

        for (String line : readFile.ReadFileByLines()) {
            String[] values = line.split(";");
            if (values.length >= 3) { // Assuming there are at least three values in each line
                String fname = values[0];
                String lname = values[1];

                // Use regex to extract the text before "!!" from the third value
                String survID = extractSurveyID(values[2]);

                viewResponseModel model = new viewResponseModel(fname, lname, survID);
                data.add(model);
            }
        }

        grid1.setItems(data);
    }

    private String extractSurveyID(String value) {
        // Use regex pattern to extract the text before "!!"
        Matcher matcher = Pattern.compile("^(.*?)(?:!!)").matcher(value);

        // Check if the pattern is found
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            // Return the original value if the pattern is not found
            return value;
        }
    }
    
    @FXML
    void handleBackClick(ActionEvent event) {
        try {
            App.setRoot("adminView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FirstNameID.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getFname()));
        LastNameID.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getLname()));
        SurveyID.setCellValueFactory(clbck -> new ReadOnlyStringWrapper(clbck.getValue().getSurvID()));
         grid1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                try {
                    String surveyID = newSelection.getSurvID();
                    if (!surveyID.isEmpty()) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("answersView.fxml"));
                        Parent root = loader.load();
                        AnswerViewController destinationController = loader.getController(); 
                        destinationController.setSurveyId(surveyID);
                        destinationController.setFirstName(newSelection.getFname());
                        destinationController.setLastName(newSelection.getLname());
                        destinationController.setSurveyName(newSelection.getFname() +" "+ newSelection.getLname());
                        destinationController.setBackPage("viewResponses");
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
}

