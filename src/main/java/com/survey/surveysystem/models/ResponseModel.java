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
public class ResponseModel {
private static String txtFirstName;
private static String txtLastName;
private static String gender;


    public void setFirstName(String txtFirstName) {
        this.txtFirstName = txtFirstName;
    }

    public void setLastName(String txtLastName) {
        this.txtLastName = txtLastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public static String getGender() {
        return gender;
    }

    public static String getFirstName() {
        return txtFirstName;
    }

    public static String getLastName() {
        return txtLastName;
    }
}
