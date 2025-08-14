package com.example.lab1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.Objects;

public class LayoutPlayGround extends BorderPane {

    /***
     *
     * Creates the content for layout playground demonstration Borderpane, GridPane, FlowPane sections etc.
     */
    public LayoutPlayGround(Scene scene) {

        BorderPane borderPaneSection = createBorderPaneSection(scene);
        GridPane gridPaneSection = createGridPaneSection();
        FlowPane flowPaneSection = createFlowPaneSection();


        // Combine all Sections
        VBox mainBox = new VBox(15);
        mainBox.setPadding(new Insets(10));

        TitledPane borderPaneTitled = new TitledPane("BorderPane Section", borderPaneSection);
        borderPaneTitled.setExpanded(true);

        TitledPane gridPaneTitled = new TitledPane("GridPane Form", gridPaneSection);
        gridPaneTitled.setExpanded(true);

        TitledPane flowPaneTitled = new TitledPane("FlowPane Buttons", flowPaneSection);
        flowPaneTitled.setExpanded(true);

        mainBox.getChildren().addAll(borderPaneTitled, gridPaneTitled, flowPaneTitled);

        // Set VBox into center of LayoutPlayground root (BorderPane)
        this.setCenter(mainBox);
    }

    /***
     * Contains logic for flow pane section.
     * @return Flow pane section
     */
    private FlowPane createFlowPaneSection() {
        FlowPane flowButtons = new FlowPane(10, 10);
        flowButtons.setPadding(new Insets(10));
        flowButtons.setPrefWrapLength(200); // Auto-wrap
        for (int i = 1; i <= 6; i++) {
            flowButtons.getChildren().add(new Button("Button " + i));
        }
        return flowButtons;
    }


    /***
     * Contains Grid pane section.
     * @return Grid pane object.
     */
    private GridPane createGridPaneSection() {

        //Gridform section
        GridPane gridForm = new GridPane();
        gridForm.setPadding(new Insets(10));
        gridForm.setHgap(10);
        gridForm.setVgap(10);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            System.out.println("Name: " + nameField.getText());
            System.out.println("Email: " + emailField.getText());
        });

        gridForm.addRow(0, nameLabel, nameField);
        gridForm.addRow(1, emailLabel, emailField);
        gridForm.add(submitButton, 1, 2);
        return gridForm;

    }

    /***
     *contains border pane logic.
     * @return Border pane object
     */
    private BorderPane createBorderPaneSection(Scene scene) {
        Label inputLabel = new Label("Enter text:");
        TextField inputField = new TextField();
        Button addButton = new Button("Add");

        ListView<String> listView = new ListView<>();
        addButton.setOnAction(e -> {
            String text = inputField.getText().trim();
            if (!text.isEmpty()) {
                listView.getItems().add(text);
                inputField.clear();
            }
        });

        HBox topBar = new HBox(10, inputLabel, inputField, addButton);
        topBar.setPadding(new Insets(10));
        topBar.setAlignment(Pos.CENTER_LEFT);

        //Theme selector logic
        ComboBox<String> themeBox = new ComboBox<>();
        themeBox.getItems().addAll("Light", "Dark");
        themeBox.setPromptText("Select Theme");

        themeBox.setOnAction(e -> {
            scene.getStylesheets().clear();
            if ("Light".equals(themeBox.getValue())) {
                scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/light-theme.css")).toExternalForm());
            } else {
                scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/dark-theme.css")).toExternalForm());
            }
        });


        CheckBox enableCheckBox = new CheckBox("Enable theme");
        enableCheckBox.setOnAction(e -> {
            System.out.println("Wrap feature: " + enableCheckBox.isSelected());
        });

        VBox rightBox = new VBox(10, themeBox, enableCheckBox);
        rightBox.setPadding(new Insets(10));
        rightBox.setAlignment(Pos.TOP_CENTER);

        // Assemble BorderPane
        BorderPane borderPaneSection = new BorderPane();
        borderPaneSection.setTop(topBar);
        borderPaneSection.setCenter(listView);
        borderPaneSection.setRight(rightBox);
        return borderPaneSection;
    }
}
