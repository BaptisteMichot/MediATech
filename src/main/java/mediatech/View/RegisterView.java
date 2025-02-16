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
    private TextField lastNameField;
    private TextField firstNameField;
    private TextField emailField;
    private PasswordField passwordField;

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
        
        lastNameField = new TextField();
        lastNameField.setMaxWidth(300);
        lastNameField.setStyle("-fx-font-size: 18px;");
        lastNameField.setPromptText("Nom");
        
        firstNameField = new TextField();
        firstNameField.setMaxWidth(300);
        firstNameField.setStyle("-fx-font-size: 18px;");
        firstNameField.setPromptText("Prénom");
        
        emailField = new TextField();
        emailField.setMaxWidth(300);
        emailField.setStyle("-fx-font-size: 18px;");
        emailField.setPromptText("Email");
        
        passwordField = new PasswordField();
        passwordField.setMaxWidth(300);
        passwordField.setStyle("-fx-font-size: 18px;");
        passwordField.setPromptText("Mot de passe");

        Region spacer = new Region();
        spacer.setMinHeight(20);

        Button registerButton = new Button("S'inscrire");
        registerButton.setStyle("-fx-font-size: 25px; -fx-background-color: #1cb84c; -fx-text-fill: white;");
        registerButton.setOnAction(e -> controller.handleRegister(lastNameField.getText(), 
            firstNameField.getText(), emailField.getText(), passwordField.getText()));

        Button goBackButton = new Button("Retour");
        goBackButton.setStyle("-fx-font-size: 25px; -fx-background-color: #1ab2d1; -fx-text-fill: white;");
        goBackButton.setOnAction(e -> returnToLoginView());

        layout.getChildren().addAll(titleLabel, lastNameField, firstNameField, emailField, 
            passwordField, spacer, registerButton, goBackButton);

        Scene scene = new Scene(layout, 600, 600);
        stage.setTitle("Inscription");
        stage.setScene(scene);
        stage.show();
    }

    public void showRegistrationSuccess() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Inscription");
        alert.setHeaderText(null);
        alert.setContentText("Inscription réussie !");
        alert.showAndWait();
        returnToLoginView();
    }

    public void showRegistrationError(boolean[] invalidFields) {
        //reset styles to not have red borders on valid fields after several attempts
        lastNameField.setStyle("-fx-font-size: 18px;");
        firstNameField.setStyle("-fx-font-size: 18px;");
        emailField.setStyle("-fx-font-size: 18px;");
        passwordField.setStyle("-fx-font-size: 18px;");

        StringBuilder errorMessage = new StringBuilder("Échec de l'inscription.\n");
        
        if (invalidFields[0]) {
            lastNameField.setStyle("-fx-font-size: 18px; -fx-border-color: red;");
            errorMessage.append("Le nom est invalide.\n");
        }
        if (invalidFields[1]) {
            firstNameField.setStyle("-fx-font-size: 18px; -fx-border-color: red;");
            errorMessage.append("Le prénom est invalide.\n");
        }
        if (invalidFields[2]) {
            emailField.setStyle("-fx-font-size: 18px; -fx-border-color: red;");
            errorMessage.append("Le format de l'adresse mail est incorrect.\n");
        }
        if (invalidFields[3]) {
            passwordField.setStyle("-fx-font-size: 18px; -fx-border-color: red;");
            errorMessage.append("Le mot de passe doit avoir au minimum 5 caractères et contenir une lettre et un chiffre.\n");
        }  

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Inscription");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage.toString().trim());
        alert.showAndWait();
    }

    private void returnToLoginView() {
        new LoginView(stage);
    }
}