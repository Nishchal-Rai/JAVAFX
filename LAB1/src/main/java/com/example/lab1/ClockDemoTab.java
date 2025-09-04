package com.example.lab1;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ClockDemoTab {
    /***
     * creates the tab to run the clock with start and stop button
     * @return Node object
     */
    public static Node createContent() {
        ClockPaneTab clock = new ClockPaneTab();
        clock.setPrefSize(300, 300);
        VBox.setVgrow(clock, javafx.scene.layout.Priority.ALWAYS);
        clock.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        Button startBtn = new Button("Start");
        Button stopBtn = new Button("Stop");

        HBox hbox = new HBox(10); // Spacing between nodes
        hbox.getChildren().addAll(startBtn, stopBtn);

        // Set a margin of 20 pixels on all sides for button1
        HBox.setMargin(startBtn, new Insets(0, 0, 10, 50));

        // Set a margin of 10 pixels on the top and bottom, and 50 on the left
        // No margin on the right
        HBox.setMargin(stopBtn, new Insets(0, 0, 10, 50));

        startBtn.setOnAction(e -> clock.setVisible(true));
        stopBtn.setOnAction(e -> clock.setVisible(false));

        return new VBox(10, clock, hbox);
    }
}
