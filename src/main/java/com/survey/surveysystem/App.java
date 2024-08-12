package com.survey.surveysystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.transform.Scale;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static String userType;

    
    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("loginView"));
        stage.setScene(scene);
        stage.show();

    }

    static void setRoot(String fxml) throws IOException {

//        scene.setRoot(loadFXML(fxml));
        Parent root = loadFXML(fxml);

        if (fxml.equals("adminAboutView")) {
            Stage stage = (Stage) scene.getWindow();
            stage.setWidth(507); // Set the preferred width
            stage.setHeight(640); // Set the preferred height
            System.out.println(userType);
        }
        else if(fxml.equals("scAboutView"))
        {
            Stage stage = (Stage) scene.getWindow();
            stage.setWidth(496); // Set the preferred width
            stage.setHeight(635); // Set the preferred height
            System.out.println(userType);
        }
        else {
            Stage stage = (Stage) scene.getWindow();
            stage.setWidth(810); // Set the preferred width
            stage.setHeight(645); // Set the preferred height
        }

        scene.setRoot(root);
    }

    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class
                .getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static String getUserType() {
        return userType;
    }

    public static void setUserType(String userType) {
        App.userType = userType;
    }
    
    public static void main(String[] args) {
        launch();
    }

    public static void setScene(Scene scene) {
        App.scene = scene;
    }

    public static Scene getScene() {
        return scene;
    }

}
