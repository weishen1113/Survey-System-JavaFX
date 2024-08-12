/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.survey.surveysystem;

import com.survey.surveysystem.file.ReadFile;
import com.survey.surveysystem.models.ResponseModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author a
 */
public class AnswerViewController implements Initializable {

    @FXML
    private Text txtTitle;

    @FXML
    private TableView<AnswerModel> tableView;

    @FXML
    private TableColumn<AnswerModel, String> questionIDColumn;

    @FXML
    private TableColumn<AnswerModel, String> questionColumn;

    @FXML
    private TableColumn<AnswerModel, String> responseAnswerColumn;

    private String surveyId;
    private String surveyName;
    private String backPage;
    private String txtQAns1Value;
    private String txtQAns2Value;
    private String txtQAns3Value;
    private String txtQAns4Value;
    private String selectedAnswer;
    private String gender;
    private String firstName;
    private String lastName;
    private ArrayList<Object> question = new ArrayList<>();

    private ObservableList<AnswerModel> questionList = FXCollections.observableArrayList();

    public void setTxtQAnsValues(String txtQAns1, String txtQAns2, String txtQAns3, String txtQAns4) {
        this.txtQAns1Value = txtQAns1;
        this.txtQAns2Value = txtQAns2;
        this.txtQAns3Value = txtQAns3;
        this.txtQAns4Value = txtQAns4;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        questionIDColumn.setCellValueFactory(new PropertyValueFactory<>("questionID"));
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
        responseAnswerColumn.setCellValueFactory(new PropertyValueFactory<>("responseAnswer"));

        tableView.setItems(questionList);
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
        String response = getResponses();
        String[] temp = response.split(";;", 2);
        String[] answers = temp[1].split(";");
        for (String line : readFile.ReadFileByLines()) {
            // TODO Adding new key to hashmap based on s[0] and set value to empty string ""
            String[] s = line.split(";");
            if (s.length >= 5) {
                if (s[2].equals(surveyId)) {
                    AnswerModel answerModel = new AnswerModel("Q" + count, s[3], answers[count-1]);
                    questionList.add(answerModel);
                    count++;
                }
            }
        }
    }

    private String getResponses() {
        ReadFile readFile = new ReadFile("respo.txt");
        //System.out.println("Getting according to " + this.firstName + " " + this.lastName + " " + this.surveyId);
        int count = 1;
        for (String line : readFile.ReadFileByLines()) {
            // Print each line to see what is being read
            //System.out.println("Line read: " + line);

            // Split the line using ";" as the delimiter
            String[] values = line.split(";");

            //System.out.println("Values Length: "+values.length);

            // Check if there are at least three values in the line
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

            //System.out.println("This is Val0"+values[0]+" This is Val1"+values[1]+" This is Val2"+values[2]);

                // Check if the trimmed values match the criteria
                if (fN.equals(this.firstName) &&
                    lN.equals(this.lastName) &&
                    sId.equals(this.surveyId)) {
                    // If all three values match, print the whole line
                    //System.out.println("We got this string: " + line);
                    return line; // You can return the line if needed
                }
            }
        }
        return null;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
        public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public void setBackPage(String backpage) {
        this.backPage = backpage;
    }

    public static class AnswerModel {
        private final SimpleStringProperty questionID;
        private final SimpleStringProperty question;
        private final SimpleStringProperty responseAnswer;

        public AnswerModel(String questionID, String question, String responseAnswer) {
            this.questionID = new SimpleStringProperty(questionID);
            this.question = new SimpleStringProperty(question);
            this.responseAnswer = new SimpleStringProperty(responseAnswer);
        }

        public String getQuestionID() {
            return questionID.get();
        }

        public String getQuestion() {
            return question.get();
        }

        public String getResponseAnswer() {
            return responseAnswer.get();
        }
    }
}