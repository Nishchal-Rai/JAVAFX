module com.example.lab1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.lab1 to javafx.fxml;
    exports com.example.lab1;
}