module org.example.assignment3_localized_database {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.assignment3_localized_database to javafx.fxml;
    exports org.example.assignment3_localized_database;
}