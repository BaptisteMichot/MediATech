package mediatech.View;

import mediatech.Controller.ChangePasswordController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChangePasswordView {
    private Stage stage;
    private ChangePasswordController controller;

    private TextField emailField;
    private PasswordField currentPasswordField;
    private PasswordField newPasswordField;

    public ChangePasswordView(Stage stage) {
        this.stage = stage;
        this.controller = new ChangePasswordController(this);
        initView();
    }

    private void initView() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Changer de mot de passe");
        titleLabel.setStyle("-fx-font-size: 55px; -fx-font-weight: bold;");

        emailField = new TextField();
        emailField.setMaxWidth(300);
        emailField.setStyle("-fx-font-size: 18px;");
        emailField.setPromptText("Adresse email");

        currentPasswordField = new PasswordField();
        currentPasswordField.setMaxWidth(300);
        currentPasswordField.setStyle("-fx-font-size: 18px;");
        currentPasswordField.setPromptText("Mot de passe actuel");

        newPasswordField = new PasswordField();
        newPasswordField.setMaxWidth(300);
        newPasswordField.setStyle("-fx-font-size: 18px;");
        newPasswordField.setPromptText("Nouveau mot de passe");

        Region spacer = new Region();
        spacer.setMinHeight(20);

        Button changePasswordButton = new Button("Changer le mot de passe");
        changePasswordButton.setStyle("-fx-font-size: 25px; -fx-background-color: #1cb84c; -fx-text-fill: white;");
        changePasswordButton.setOnAction(e -> controller.handleChangePassword(emailField.getText(), currentPasswordField.getText(), newPasswordField.getText()));

        Button goBackButton = new Button("Retour");
        goBackButton.setStyle("-fx-font-size: 25px; -fx-background-color: #1ab2d1; -fx-text-fill: white;");
        goBackButton.setOnAction(e -> returnToLoginView());

        layout.getChildren().addAll(titleLabel, emailField, currentPasswordField, newPasswordField, spacer, changePasswordButton, goBackButton);

        Scene scene = new Scene(layout, 1530, 780);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setTitle("Changer de mot de passe");        
        stage.show();
    }

    public void showChangePasswordSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Changement de mot de passe");
        alert.setHeaderText(null);
        alert.setContentText("Mot de passe modifié avec succès !");
        alert.showAndWait();
        returnToLoginView();
    }

    public void showChangePasswordError(String errorType) {
        String message;
        switch (errorType) {
            case "errorLogin":
                message = "Email ou mot de passe incorrect.";
                break;
            case "errorNewPassword":
                message = "Le nouveau mot de passe est invalide. Il doit être différent et contenir au minimum 5 caractères, une lettre et un chiffre.";
                break;
            case "error":
                message = "Erreur lors du changement de mot de passe.";
                break;
            default:
                message = "Erreur lors du changement de mot de passe.";
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Changement de mot de passe");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void returnToLoginView() {
        new LoginView(stage);
    }
}