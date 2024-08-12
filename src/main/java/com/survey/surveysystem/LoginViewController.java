/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.survey.surveysystem;

import com.survey.surveysystem.auth.User;
import com.survey.surveysystem.file.ReadFile;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author a
 */
public class LoginViewController implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;
    public String userType;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void login(ActionEvent event) {
        try {
            boolean isUserExist = false;
            if (!txtUserName.getText().isEmpty() && !txtPassword.getText().isEmpty()) {
                ReadFile readFile = new ReadFile("admin.txt");
                for (String line : readFile.ReadFileByLines()) {
                    if (!line.isEmpty() && line.contains(";")) {
                        String[] s = line.split(";");
                        System.out.println(s.length);
                        if (s.length == 4) {
                            if (s[1].equals(txtUserName.getText()) && s[2].equals(txtPassword.getText())) {
                                App.setRoot("adminView");
                                App.setUserType("admin");
                                User.loggedInUser = s[0];
                                User.userName = s[1];
                                isUserExist = true;
                                break;
                            }
                        }
                    }
                }
                if (!isUserExist) {
                    Alert alert = new Alert(AlertType.ERROR, "Username or password is wrong.");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR, "Please Enter All Details.");
                alert.show();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void onSCClick(ActionEvent event) {
        try {
            App.setRoot("scLoginView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void onSubmitSurvey(ActionEvent event) {
         try {
            App.setRoot("clientLoginView");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
