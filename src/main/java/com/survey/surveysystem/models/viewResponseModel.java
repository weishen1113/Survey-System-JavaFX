/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.survey.surveysystem.models;

/**
 *
 * @author a
 */
public class viewResponseModel {

    private String Fname;
    private String Lname;
    private String SurvID;


    public viewResponseModel(String Fname, String Lname, String SurvID) {
        this.Fname = Fname;
        this.Lname = Lname;
        this.SurvID = SurvID;
    }


    public String getFname() {
        return Fname;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    
    public String getLname() {
        return Lname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }


    public String getSurvID() {
        return SurvID;
    }

    public void setSurvID(String SurvID) {
        this.SurvID = SurvID;
    }

}
