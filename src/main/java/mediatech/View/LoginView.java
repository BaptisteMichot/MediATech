package mediatech.View;

import mediatech.Controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

public class LoginView {
    private Stage stage;
    private LoginController controller;

    public LoginView(Stage stage) {
        this.stage = stage;
        this.controller = new LoginController(this);
        initView();
    }

    private void initView() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Connexion");
        titleLabel.setStyle("-fx-font-size: 55px; -fx-font-weight: bold;");

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

        Button loginButton = new Button("Se connecter");
        loginButton.setStyle("-fx-font-size: 25px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        loginButton.setOnAction(e -> controller.handleLogin(emailField.getText(), passwordField.getText()));

        Button registerButton = new Button("S'inscrire");
        registerButton.setStyle("-fx-font-size: 25px; -fx-background-color: #2196F3; -fx-text-fill: white;");
        registerButton.setOnAction(e -> openRegisterView());

        layout.getChildren().addAll(titleLabel, emailField, passwordField, spacer,loginButton, registerButton);

        Scene scene = new Scene(layout, 600, 600);
        stage.setTitle("MediaTech - Connexion");
        stage.setScene(scene);
        //full screen
        stage.setWidth(javafx.stage.Screen.getPrimary().getVisualBounds().getWidth());
        stage.setHeight(javafx.stage.Screen.getPrimary().getVisualBounds().getHeight());
        stage.show();
    }

    public void openRegisterView() {
        new RegisterView(stage); 
    }

    public void showLoginSuccess() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Connexion");
        alert.setHeaderText(null);
        alert.setContentText("Connexion réussie!");
        alert.showAndWait();
    }

    public void showLoginError() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Connexion");
        alert.setHeaderText(null);
        alert.setContentText("Échec de connexion, vérifiez vos identifiants.");
        alert.showAndWait();
    }
}
