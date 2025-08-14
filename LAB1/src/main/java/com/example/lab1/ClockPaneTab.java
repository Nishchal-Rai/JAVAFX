package com.example.lab1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.time.LocalTime;

public class ClockPaneTab extends Pane {

    private IntegerProperty hour = new SimpleIntegerProperty();
    private IntegerProperty minute = new SimpleIntegerProperty();
    private IntegerProperty second = new SimpleIntegerProperty();

    private Circle clockFace;
    private Line hourHand, minuteHand, secondHand;
    private Timeline timeline;

    public ClockPaneTab() {
        clockFace = new Circle(1000, Color.WHITE);
        clockFace.setStroke(Color.BLACK);

        hourHand = new Line();
        minuteHand = new Line();
        secondHand = new Line();

        getChildren().addAll(clockFace, hourHand, minuteHand, secondHand);

        // Timeline updates every second
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTime()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        updateTime();

        widthProperty().addListener(e -> resize());
        heightProperty().addListener(e -> resize());
    }

    private void updateTime() {
        LocalTime now = LocalTime.now();
        hour.set(now.getHour() % 12);
        minute.set(now.getMinute());
        second.set(now.getSecond());
        drawHands();
    }

    private void drawHands() {
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;
        double radius = Math.min(centerX, centerY) - 10;

        clockFace.setCenterX(centerX);
        clockFace.setCenterY(centerY);
        clockFace.setRadius(radius);



        // Remove old numbers before drawing
        getChildren().removeIf(node -> node instanceof Text);

        // Draw numbers 1 to 12
        for (int num = 1; num <= 12; num++) {
            double angle = Math.toRadians(num * 30 - 90); // -90 so 12 is at the top
            double textX = centerX + (radius - 25) * Math.cos(angle);
            double textY = centerY + (radius - 25) * Math.sin(angle) + 5; // +5 to center text vertically
            Text t = new Text(textX - 5, textY, String.valueOf(num)); // -5 to center horizontally
            getChildren().add(t);
        }

        double secondAngle = second.get() * 6;
        double minuteAngle = minute.get() * 6;
        double hourAngle = (hour.get() + minute.get() / 60.0) * 30;

        setHand(hourHand, centerX, centerY, radius * 0.5, hourAngle);
        setHand(minuteHand, centerX, centerY, radius * 0.7, minuteAngle);
        setHand(secondHand, centerX, centerY, radius * 0.9, secondAngle);
    }

    private void setHand(Line hand, double centerX, double centerY, double length, double angle) {
        double endX = centerX + length * Math.sin(Math.toRadians(angle));
        double endY = centerY - length * Math.cos(Math.toRadians(angle));
        hand.setStartX(centerX);
        hand.setStartY(centerY);
        hand.setEndX(endX);
        hand.setEndY(endY);
        hand.setStrokeWidth(2);
    }

    private void resize() {
        drawHands();
    }
}
