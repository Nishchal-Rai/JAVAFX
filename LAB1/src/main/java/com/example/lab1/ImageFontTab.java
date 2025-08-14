package com.example.lab1;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.Objects;

public class ImageFontTab {
    /***
     * Creates the content for resizable font and image.
     * @return Node object.
     */
    public static Node createContent() {
        VBox box = new VBox(10);
        Label header = new Label("Image & Font Tab");
        header.setFont(Font.font("Verdana", 20));

        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        Slider sizeSlider = new Slider(100, 500, 200);
        imageView.fitWidthProperty().bind(sizeSlider.valueProperty());

        try {
            Image img = new Image(Objects.requireNonNull(ImageFontTab.class.getResource("/images/java.png")).toExternalForm());
            imageView.setImage(img);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Image not found!. Please check the file path.");
            alert.showAndWait();
        }
        box.getChildren().addAll(header, imageView, new Label("Resize:"), sizeSlider);
        return new ScrollPane(box);
    }
}

