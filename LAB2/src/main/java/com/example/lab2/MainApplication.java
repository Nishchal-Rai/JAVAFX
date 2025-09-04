package com.example.lab2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        CircleAnimation circleAnimation = new CircleAnimation();
        Scene scene = new Scene(circleAnimation, 400, 300);


        // Show the stage
        stage.setTitle("Circle Control App");
        stage.setScene(scene);
        stage.show();
        circleAnimation.requestFocus();


    }

    public static void main(String[] args) {
        launch();
    }
}