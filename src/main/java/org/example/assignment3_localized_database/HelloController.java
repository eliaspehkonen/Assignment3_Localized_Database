package org.example.assignment3_localized_database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.*;

import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private Label lbl1;

    @FXML
    private Label lbl;

    @FXML
    private Label lbl2;

    @FXML
    private Label lbl3;

    @FXML
    private TextField firstnamefield;

    @FXML
    private TextField lastnamefield;

    @FXML
    private TextField emailfield;

    @FXML
    private ComboBox<String> languageComboBox;

    @FXML
    private Button saveButton;

    private ResourceBundle bundle;
    private Locale locale;
    private Stage stage;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/Assignment_3_Localized_database";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "<password>";

    public HelloController(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {
        // Load default language when the controller is initialized
        loadLanguage("en", "UK");
    }

    @FXML
    private void loadLanguage(String lang, String country) {
        locale = new Locale(lang, country);
        bundle = ResourceBundle.getBundle("messages", locale);

        lbl1.setText(bundle.getString("select"));
        lbl.setText(bundle.getString("firstname"));
        lbl2.setText(bundle.getString("lastname"));
        lbl3.setText(bundle.getString("email"));

        // Update the stage title dynamically
        if (stage != null) {
            stage.setTitle(bundle.getString("title"));
        }

        saveButton.setText(bundle.getString("save"));
    }

    @FXML
    private void onLanguageSelected(ActionEvent event) {
        String selectedLanguage = languageComboBox.getSelectionModel().getSelectedItem();
        switch (selectedLanguage) {
            case "English":
                loadLanguage("en", "UK");
                break;
            case "Japanese":
                loadLanguage("ja", "JP");
                break;
            case "Persian":
                loadLanguage("fa", "IR");
                break;
            default:
                break;
        }
    }

    @FXML
    private void save_employee(ActionEvent event) {
        String first_name = firstnamefield.getText();
        String last_name = lastnamefield.getText();
        String email = emailfield.getText();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String tableName = "employee_" + locale.getLanguage().toLowerCase(); // Get the language code and concatenate it with "employee_" to form the table name
            String sql = "INSERT INTO " + tableName + " (first_name, last_name, email) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, first_name);
                statement.setString(2, last_name);
                statement.setString(3, email);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        long id = generatedKeys.getLong(1);
                        System.out.println("Employee saved with id: " + id);
                        showAlert(AlertType.INFORMATION, bundle.getString("employee_saved"));

                    }
                } else {
                    System.out.println("Failed to save employee");
                    showAlert(AlertType.ERROR, bundle.getString("failed_to_save_employee"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(bundle.getString("alert"));
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
