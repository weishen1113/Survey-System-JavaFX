/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.survey.surveysystem.models;

/**
 *
 * @author a
 */
public class QuestionModel {

    private String id;
    private int postion;
    private String surveyId;
    private String text;
    private String type;
    private String MCQoptions;
    
    public QuestionModel(String id, int postion, String surveyId, String text, String type) {
        this.id = id;
        this.postion = postion;
        this.surveyId = surveyId;
        this.text = text;
        this.type = type;
    }
    
    public QuestionModel(String id, int postion, String surveyId, String text, String type,String op1,String op2, String op3, String op4) {
        this.id = id;
        this.postion = postion;
        this.surveyId = surveyId;
        this.text = text;
        this.type = type;
        this.MCQoptions = op1+"#"+op2+"#"+op3+"#"+op4 ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public void setMCQOptions(String MCQoption){
        this.MCQoptions = MCQoption;
    }
    
    public String getMCQOptions(){
        return MCQoptions;
    }
}
