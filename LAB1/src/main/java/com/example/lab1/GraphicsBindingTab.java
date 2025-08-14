package com.example.lab1;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class GraphicsBindingTab {

    public static Node createContent() {
        Pane circlePane = new Pane();
        Circle circle = new Circle(50, Color.CORNFLOWERBLUE);
        circle.centerXProperty().bind(circlePane.widthProperty().divide(2));
        circle.centerYProperty().bind(circlePane.heightProperty().divide(2));
        circlePane.getChildren().add(circle);

        // Radius Slider (unidirectional binding)
        Slider radiusSlider = new Slider(10, 200, 50);
        circle.radiusProperty().bind(radiusSlider.valueProperty());

        // Caption (bidirectional binding)
        TextField captionField = new TextField();
        Label captionLabel = new Label();
        captionField.textProperty().bindBidirectional(captionLabel.textProperty());

        // Rotation slider
        Slider rotationSlider = new Slider(0, 360, 0);
        circle.rotateProperty().bind(rotationSlider.valueProperty());

        // Pulse animation
        Button pulseBtn = new Button("Pulse");
        pulseBtn.setOnAction(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(500), circle);
            st.setByX(0.3);
            st.setByY(0.3);
            st.setAutoReverse(true);
            st.setCycleCount(2);
            st.play();
        });

        // Color picker
        ColorPicker colorPicker = new ColorPicker(Color.CORNFLOWERBLUE);
        colorPicker.setOnAction(e -> circle.setFill(colorPicker.getValue()));

        VBox controls = new VBox(10,
                new Label("Radius:"), radiusSlider,
                new Label("Caption:"), captionField, captionLabel,
                new Label("Rotation:"), rotationSlider,
                colorPicker, pulseBtn
        );
        controls.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setCenter(circlePane);
        root.setRight(controls);

        return root;
    }
}
