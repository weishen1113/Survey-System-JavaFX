/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.survey.surveysystem.models;

/**
 *
 * @author a
 */
public class SurveyCreatorModel {

    private String scId;
    private String firstName;
    private String lastName;
    private String faculity;
    private String email;
    private String phone;
    private String gender;

    public SurveyCreatorModel(String scId, String firstName, String lastName, String faculity, String email, String phone, String gender) {
        this.scId = scId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.faculity = faculity;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }

    public String getScId() {
        return scId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFaculity() {
        return faculity;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public void setScId(String scId) {
        this.scId = scId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFaculity(String faculity) {
        this.faculity = faculity;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
