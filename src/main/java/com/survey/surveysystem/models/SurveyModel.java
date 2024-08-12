/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.survey.surveysystem.models;

/**
 *
 * @author a
 */
public class SurveyModel {

    private String scID;
    private String creatorName;
    private String surveyID;
    private String surveyTitle;
    private long responses;

    public SurveyModel(String scID, String creatorName, String surveyID, String surveyTitle, long responses) {
        this.surveyID = surveyID;
        this.surveyTitle = surveyTitle;
        this.scID = scID;
        this.creatorName = creatorName;
        this.responses = responses;
    }

    public String getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(String surveyID) {
        this.surveyID = surveyID;
    }

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }

    public String getScID() {
        return scID;
    }

    public void setScID(String scID) {
        this.scID = scID;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public long getResponses() {
        return responses;
    }

    public void setResponses(long responses) {
        this.responses = responses;
    }
}
