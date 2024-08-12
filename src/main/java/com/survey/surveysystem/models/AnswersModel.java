/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.survey.surveysystem.models;

import javafx.scene.control.TextField;

/**
 *
 * @author User
 */
public class AnswersModel {
private static String QuestionID;
private static String Question;
private static String ResponseAnswer;


    public void setQuestionID(String QuestionID) {
        this.QuestionID = QuestionID;
    }

    public void setLastName(String Question) {
        this.Question = Question;
    }

    public void setResponseAnswer(String ResponseAnswer) {
        this.ResponseAnswer = ResponseAnswer;
    }

    public static String getQuestionID() {
        return QuestionID;
    }

    public static String getQuestion() {
        return Question;
    }

    public static String getResponseAnswer() {
        return ResponseAnswer;
    }
}
