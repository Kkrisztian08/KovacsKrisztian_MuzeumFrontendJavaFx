module com.example.muzeumfrontendjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.muzeumfrontendjavafx to javafx.fxml, com.google.gson;
    exports com.example.muzeumfrontendjavafx;
    opens com.example.muzeumfrontendjavafx.paintings to com.google.gson, javafx.fxml;
    exports com.example.muzeumfrontendjavafx.paintings;
    exports com.example.muzeumfrontendjavafx.statues;
    opens com.example.muzeumfrontendjavafx.statues to com.google.gson, javafx.fxml;
}