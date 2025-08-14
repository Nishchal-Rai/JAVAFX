package com.example.lab1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);
        MenuBar menuBar = new MenuBar();
        Menu helpMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setOnAction(e -> showAboutDialog());
        helpMenu.getItems().add(aboutItem);

        //Adding items on TabPane.
        TabPane tabPane = new TabPane();
        Tab playgroundTab = new Tab("Layout Playground", new LayoutPlayGround(scene));
        Tab graphicsTab = new Tab("Graphics & Binding", GraphicsBindingTab.createContent());
        Tab imageFontTab = new Tab("Images & Font", ImageFontTab.createContent());
        Tab clockDemoTab = new Tab("Clock", ClockDemoTab.createContent());
        playgroundTab.setClosable(false);

        tabPane.getTabs().add(playgroundTab);
        tabPane.getTabs().add(graphicsTab);
        tabPane.getTabs().add(imageFontTab);
        tabPane.getTabs().add(clockDemoTab);

        root.setCenter(tabPane);
        menuBar.getMenus().add(helpMenu);
        root.setTop(menuBar);


        stage.setTitle("JavaFX Mini Studio");
        stage.setMinWidth(500);
        stage.setMinHeight(400);
        stage.setScene(scene);
        stage.show();

    }

    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("JavaFX Showcase App");
        alert.setContentText("Created for JAVA fx Exploration.");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}