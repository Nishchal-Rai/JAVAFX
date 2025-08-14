
public class Main extends Application{
    @Override
    public void start(Stage primaryStage) {
        // Create a Label Node
        Label helloLabel = new Label("Hello, JavaFX!");

        // Create a layout Pane (StackPane centers its children)
        StackPane root = new StackPane();
        root.getChildren().add(helloLabel);

        // Create a Scene with the root Pane and specify its dimensions
        Scene scene = new Scene(root, 300, 200);

        // Set the Scene on the Stage
        primaryStage.setScene(scene);

        // Set the title of the Stage (window)
        primaryStage.setTitle("Hello JavaFX App");

        // Show the Stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}