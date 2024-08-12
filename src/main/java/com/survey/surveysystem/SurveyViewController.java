/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.survey.surveysystem;

import com.survey.surveysystem.file.ReadFile;
import com.survey.surveysystem.file.WriteFile;
import com.survey.surveysystem.models.ResponseModel;
import com.survey.surveysystem.models.viewResponseModel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;

/**
 * FXML Controller class
 *
 * @author a
 */
public class SurveyViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public VBox root;
    private String surveyId;
    private String surveyName;
    private String backPage;
    private String txtQAns1Value;
    private String txtQAns2Value;
    private String txtQAns3Value;
    private String txtQAns4Value;
    private String selectedAnswer;
    private String gender;
    private String firstName ;
    private String lastName;
    private ArrayList<Object> question = new ArrayList<Object>();
    
    public void setTxtQAnsValues(String txtQAns1, String txtQAns2, String txtQAns3, String txtQAns4) {
        this.txtQAns1Value = txtQAns1;
        this.txtQAns2Value = txtQAns2;
        this.txtQAns3Value = txtQAns3;
        this.txtQAns4Value = txtQAns4;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private Text txtTitle;

    @FXML
    void OnTest(ActionEvent event) {

    }

    @FXML
    void onBackClick(ActionEvent event) {
        try {
            App.setRoot(backPage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void start() {
        txtTitle.setText(surveyName);
        ReadFile readFile = new ReadFile("questions.txt");
        int count = 1;
        for (String line : readFile.ReadFileByLines()) {
            //TODO Adding new key to hashmap based on s[0] and set value to empty string ""
            String[] s = line.split(";");
            if (s.length >=5 ) {
                if (s[2].equals(surveyId)) {
                Text text = new Text("Q" + count + " " + s[3]);
                
                VBox vbox = new VBox();
                vbox.getChildren().add(text);
                
                switch(s[4]) {
                    case "PolarQuestion":
                        RadioButton yes = new RadioButton("Yes");
                        RadioButton no = new RadioButton("No");
                        yes.setUserData("Yes");
                        no.setUserData("No");
                        ToggleGroup toggleGroup1 = new ToggleGroup();
                        yes.setToggleGroup(toggleGroup1);
                        no.setToggleGroup(toggleGroup1);
                        question.add(toggleGroup1);
                        
                        HBox polarBox = new HBox();
                        polarBox.getChildren().add(yes);
                        polarBox.getChildren().add(no);
                        polarBox.setSpacing(25);
                        
                        vbox.getChildren().add(polarBox);
                        break;
                    case "MCQ":
                        RadioButton c1 = new RadioButton(s[5]);
                        RadioButton c2 = new RadioButton(s[6]);
                        RadioButton c3 = new RadioButton(s[7]);
                        RadioButton c4 = new RadioButton(s[8]);
                        c1.setUserData(s[5]);
                        c2.setUserData(s[6]);
                        c3.setUserData(s[7]);
                        c4.setUserData(s[8]);
                        ToggleGroup toggleGroup2 = new ToggleGroup();
                        c1.setToggleGroup(toggleGroup2);
                        c2.setToggleGroup(toggleGroup2);
                        c3.setToggleGroup(toggleGroup2);
                        c4.setToggleGroup(toggleGroup2);
                        question.add(toggleGroup2);
                        
                        HBox hbox = new HBox();
                        hbox.getChildren().add(c1);
                        hbox.getChildren().add(c2);
                        hbox.getChildren().add(c3);
                        hbox.getChildren().add(c4);
                        hbox.setSpacing(25);
                        
                        vbox.getChildren().add(hbox);
                        break;
                    default:
                        TextField textField = new TextField();
                        textField.setPromptText("Answer");
                        
                        vbox.getChildren().add(textField);
                        question.add(textField);
                        break;
                }
                root.getChildren().add(vbox);
                root.setSpacing(25);
                count++;
            }
        }
    }
    }
    public void setSurveyId(String surveyId){
        this.surveyId = surveyId;
        
    }
    
    public void setSurveyName(String surveyName){
        this.surveyName=surveyName;
    }
    public void setBackPage(String backpage){
        this.backPage=backpage;
    }
         
    
    @FXML
    void onSubmit(ActionEvent event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("files/respo.txt", true))) {
            
            firstName = ResponseModel.getFirstName();
            lastName = ResponseModel.getLastName();
            gender = ResponseModel.getGender();

            String ans = "";
            for (int i = 0; i < question.size(); i++) {
               if (question.get(i) instanceof ToggleGroup) {
                ans += ";" + ((ToggleGroup) question.get(i)).getSelectedToggle().getUserData().toString();
               } else{
                    ans += ";" + ((TextField) question.get(i)).getText();
                }
            }  
            if(ValidateSubmission(firstName,lastName,surveyId)){
                writer.write( firstName + ";" + lastName + ";" + surveyId + "!!" + gender + ";"+ ans);
                writer.newLine();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Submitted");
                alert.show();
                App.setRoot("surveyListView");                
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING, "You already Submitted this survey!");
                alert.show();
                App.setRoot("surveyListView");
            }
            
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    } 
    
    
        private boolean ValidateSubmission(String first,String last,String survId) {
        ReadFile readFile = new ReadFile("respo.txt");
        //System.out.println("Getting according to " + this.firstName + " " + this.lastName + " " + this.surveyId);
        int count = 1;
        for (String line : readFile.ReadFileByLines()) {
            String[] values = line.split(";");
            if (values.length >= 3) {
                // Extract the values and trim them
                String fN = values[0];
                String lN = values[1];
                String sId = values[2];

            // Extract the value before "!!" in sId
            String[] sIdParts = sId.split("!!");
            if (sIdParts.length > 0) {
                sId = sIdParts[0].trim();
            }
                if (fN.equals(first) &&
                    lN.equals(last) &&
                    sId.equals(survId)) {
                    return false; // You can return the line if needed
                }
            }
        }
        return true;
    }
}
     
        
        
        
//for(int i = 0; i < question.size(); i++) {
//            if(question.get(i) instanceof ToggleGroup) {
//                test = ((ToggleGroup) question.get(i)).getSelectedToggle().getUserData().toString();
//                break;
//            }
//           
//        }
//        StringBuilder responseBuilder = new StringBuilder();
//    responseBuilder.append(surveyId).append(";").append("submited").append(";");
//
//        ReadFile readFile = new ReadFile("questions.txt");
//        
//        //TODO Loop through ArrayList and getText of each object
//
//        int count = 1;
//        for (String line : readFile.ReadFileByLines()) {
//            String[] s = line.split(";");
//            if (s.length >= 5 && s[2].equals(surveyId)) {
//                responseBuilder.append("Q").append(count).append(": ");
//                responseBuilder.append(selectedAnswer != null ? selectedAnswer : "No answer selected");
//                responseBuilder.append("; ");
//                count++;
//            }
//        }
//
//        WriteFile writeFile = new WriteFile("responses.txt");
//        writeFile.writeFile(surveyId.concat(";").concat("submited"));
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Your response is submited");
//        alert.show();
//        try {
//            String returnToLogin = "clientLoginView";
//            App.setRoot(returnToLogin);
//        }
//        catch (IOException ex){
//            ex.printStackTrace();
//        }
    
