package com.example.lab1;

import javafx.scene.Node;
import javafx.scene.control.Button;
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

        startBtn.setOnAction(e -> clock.setVisible(true));
        stopBtn.setOnAction(e -> clock.setVisible(false));

        return new VBox(10, clock, startBtn, stopBtn);
    }
}
