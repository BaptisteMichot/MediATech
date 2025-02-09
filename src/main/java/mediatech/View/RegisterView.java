package mediatech.View;

import mediatech.Controller.RegisterController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterView {
    private Stage stage;
    private RegisterController controller;

    public RegisterView(Stage stage) {
        this.stage = stage;
        this.controller = new RegisterController(this);
        initView();
    }

    private void initView() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Inscription");
        titleLabel.setStyle("-fx-font-size: 55px; -fx-font-weight: bold;");
        
        TextField lastNameField = new TextField();
        lastNameField.setMaxWidth(300);
        lastNameField.setStyle("-fx-font-size: 18px;");
        lastNameField.setPromptText("Nom");
        
        TextField firstNameField = new TextField();
        firstNameField.setMaxWidth(300);
        firstNameField.setStyle("-fx-font-size: 18px;");
        firstNameField.setPromptText("Prénom");
        
        TextField emailField = new TextField();
        emailField.setMaxWidth(300);
        emailField.setStyle("-fx-font-size: 18px;");
        emailField.setPromptText("Email");
        
        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(300);
        passwordField.setStyle("-fx-font-size: 18px;");
        passwordField.setPromptText("Mot de passe");

        Region spacer = new Region();
        spacer.setMinHeight(20);

        Button registerButton = new Button("S'inscrire");
        registerButton.setStyle("-fx-font-size: 25px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        registerButton.setOnAction(e -> controller.handleRegister(lastNameField.getText(), 
            firstNameField.getText(), emailField.getText(), passwordField.getText()));

        Button goBackButton = new Button("Retour");
        goBackButton.setStyle("-fx-font-size: 25px; -fx-background-color: #2196F3; -fx-text-fill: white;");
        goBackButton.setOnAction(e -> returnToLoginView());

        layout.getChildren().addAll(titleLabel, lastNameField, firstNameField, emailField, 
            passwordField, spacer, registerButton, goBackButton);

        Scene scene = new Scene(layout, 600, 600);
        stage.setTitle("MediaTech - Inscription");
        stage.setScene(scene);
        //full screen
        stage.setWidth(javafx.stage.Screen.getPrimary().getVisualBounds().getWidth());
        stage.setHeight(javafx.stage.Screen.getPrimary().getVisualBounds().getHeight());
        stage.show();
    }

    public void showRegistrationSuccess() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Inscription");
        alert.setHeaderText(null);
        alert.setContentText("Inscription réussie!");
        alert.showAndWait();
    }

    public void showRegistrationError() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Inscription");
        alert.setHeaderText(null);
        alert.setContentText("Échec de l'inscription. Veuillez réessayer.");
        alert.showAndWait();
    }

    private void returnToLoginView() {
        new LoginView(stage);
    }
}