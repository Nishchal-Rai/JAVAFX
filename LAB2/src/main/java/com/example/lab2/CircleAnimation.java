package com.example.lab2;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class CircleAnimation extends BorderPane {

    private Circle circle;
    private Label statusLabel;
    private FadeTransition fadeTransition;
    private static final double MIN_RADIUS = 10;
    private static final double MAX_RADIUS = 100;
    private static final double RADIUS_CHANGE = 5;

    public CircleAnimation() {
        // Create the UI components
        circle = new Circle(50, Color.LIGHTBLUE);
        circle.setStroke(Color.LIGHTBLUE);
        statusLabel = new Label();

        Button growButton = new Button("Grow");
        Button shrinkButton = new Button("Shrink");

        // Implement Grow with a lambda
        growButton.setOnAction(e -> {
            changeRadius(RADIUS_CHANGE);
        });

        // Implement Shrink with an anonymous inner class
        shrinkButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                changeRadius(-RADIUS_CHANGE);
            }
        });

        // Button container
        HBox buttonBox = new HBox(10, growButton, shrinkButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Main layout
        VBox root = new VBox(20, circle, buttonBox, statusLabel);
        root.setAlignment(Pos.CENTER);
        this.setCenter(root);
        this.setFocusTraversable(true);

        // Keyboard event handling
        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                changeRadius(RADIUS_CHANGE);
            } else if (event.getCode() == KeyCode.DOWN) {
                changeRadius(-RADIUS_CHANGE);
            }
        });

        // Double-click handling (capture phase so buttons/circle can't consume it first)
        this.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                toggleAnimation();
                event.consume();
            }
        });

        // Setup the animation
        setupAnimation();

    }


    private void changeRadius(double delta) {
        double newRadius = circle.getRadius() + delta;
        if (newRadius >= MIN_RADIUS && newRadius <= MAX_RADIUS) {
            circle.setRadius(newRadius);
            statusLabel.setText(""); // Clear status message
        } else if (newRadius < MIN_RADIUS) {
            statusLabel.setText("Min size reached");
        } else {
            statusLabel.setText("Max size reached");
        }
    }

    private void setupAnimation() {
        fadeTransition = new FadeTransition(Duration.seconds(1), circle);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.2);
        fadeTransition.setCycleCount(FadeTransition.INDEFINITE);
        fadeTransition.setAutoReverse(true);
    }

    private void toggleAnimation() {
        if (fadeTransition.getStatus() == javafx.animation.Animation.Status.RUNNING) {
            fadeTransition.pause();
        } else {
            fadeTransition.play();
        }
    }

}


