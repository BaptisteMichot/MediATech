package mediatech.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mediatech.Model.BL.User;
import mediatech.Controller.ManagementController;

public class ManagementView {
    private Stage stage;
    private ManagementController controller;

    public ManagementView(Stage stage, User currentUser) {
        this.stage = stage;
        this.controller = new ManagementController(this);
        initView();
    }

    private void initView() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);

        showReservations();
        restocking();
        addBook();
        deleteBook();
        addDVD();
        deleteDVD();
        addBluray();
        deleteBluray();

        layout.getChildren().addAll(showReservations(), restocking(), addBook(), deleteBook(), addDVD(), deleteDVD(), addBluray(), deleteBluray());

        Scene scene = new Scene(layout, 600, 600);
        stage.setTitle("Gestion de la médiathèque");
        stage.setScene(scene);
        stage.show();
    }

    private VBox showReservations() {
        Label label = new Label("Afficher réservations");
        label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField lastNameField = new TextField();
        lastNameField.setMaxWidth(300);
        lastNameField.setStyle("-fx-font-size: 15px;");
        lastNameField.setPromptText("Nom");

        TextField firstNameField = new TextField();
        firstNameField.setMaxWidth(300);
        firstNameField.setStyle("-fx-font-size: 15px;");
        firstNameField.setPromptText("Prénom");

        Button showReservationsButton = new Button("Valider");
        showReservationsButton.setStyle("-fx-font-size: 15px; -fx-background-color: #1ab2d1; -fx-text-fill: white;");
        showReservationsButton.setOnAction(e -> controller.showReservations(lastNameField.getText(), firstNameField.getText()));

        HBox hbox = new HBox(10, lastNameField, firstNameField, showReservationsButton);
        hbox.setAlignment(Pos.TOP_LEFT);        
        VBox vbox = new VBox(20, label, hbox);
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    private VBox restocking() {
        Label label = new Label("Remettre en stock");
        label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField mediaTypeField = new TextField();
        mediaTypeField.setMaxWidth(300);
        mediaTypeField.setStyle("-fx-font-size: 15px;");
        mediaTypeField.setPromptText("Type de média");

        TextField titleField = new TextField();
        titleField.setMaxWidth(300);
        titleField.setStyle("-fx-font-size: 15px;");
        titleField.setPromptText("Titre");

        TextField stateField = new TextField();
        stateField.setMaxWidth(300);
        stateField.setStyle("-fx-font-size: 15px;");
        stateField.setPromptText("Etat");

        Button restockingButton = new Button("Valider");
        restockingButton.setStyle("-fx-font-size: 15px; -fx-background-color: #1ab2d1; -fx-text-fill: white;");
        restockingButton.setOnAction(e -> controller.restocking(mediaTypeField.getText(), titleField.getText(), stateField.getText()));

        HBox hbox = new HBox(10, mediaTypeField, titleField, stateField, restockingButton);
        hbox.setAlignment(Pos.TOP_LEFT);        
        VBox vbox = new VBox(20, label, hbox);
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    private VBox addBook() {
        Label label = new Label("Ajouter un livre");
        label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField titleField = new TextField();
        titleField.setMaxWidth(300);
        titleField.setStyle("-fx-font-size: 15px;");
        titleField.setPromptText("Titre");

        TextField stateField = new TextField();
        stateField.setMaxWidth(300);
        stateField.setStyle("-fx-font-size: 15px;");
        stateField.setPromptText("Etat");

        TextField publicationDateField = new TextField();
        publicationDateField.setMaxWidth(300);
        publicationDateField.setStyle("-fx-font-size: 15px;");
        publicationDateField.setPromptText("Date de publication");

        TextField isbnField = new TextField();
        isbnField.setMaxWidth(300);
        isbnField.setStyle("-fx-font-size: 15px;");
        isbnField.setPromptText("ISBN");

        TextField authorField = new TextField();
        authorField.setMaxWidth(300);
        authorField.setStyle("-fx-font-size: 15px;");
        authorField.setPromptText("Auteur");

        TextField publisherField = new TextField();
        publisherField.setMaxWidth(300);
        publisherField.setStyle("-fx-font-size: 15px;");
        publisherField.setPromptText("Éditeur");

        TextField pageCountField = new TextField();
        pageCountField.setMaxWidth(300);
        pageCountField.setStyle("-fx-font-size: 15px;");
        pageCountField.setPromptText("Nombre de pages");

        Button addButton = new Button("Ajouter");
        addButton.setStyle("-fx-font-size: 15px; -fx-background-color: #1ab2d1; -fx-text-fill: white;");
        addButton.setOnAction(e -> controller.addBook(titleField.getText(), stateField.getText(), publicationDateField.getText(), 
            isbnField.getText(), authorField.getText(), publisherField.getText(), pageCountField.getText()));
        
        HBox hbox = new HBox(10, titleField, stateField, publicationDateField, isbnField, authorField, publisherField, pageCountField, addButton);
        hbox.setAlignment(Pos.TOP_LEFT);
        VBox vbox = new VBox(20, label, hbox);
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    private VBox deleteBook() {
        Label label = new Label("Supprimer un livre");
        label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField titleField = new TextField();
        titleField.setMaxWidth(300);
        titleField.setStyle("-fx-font-size: 15px;");
        titleField.setPromptText("Titre");

        Button deleteButton = new Button("Supprimer");
        deleteButton.setStyle("-fx-font-size: 15px; -fx-background-color: #1ab2d1; -fx-text-fill: white;");
        deleteButton.setOnAction(e -> controller.deleteBook(titleField.getText()));
        
        HBox hbox = new HBox(10, titleField, deleteButton);
        hbox.setAlignment(Pos.TOP_LEFT);
        VBox vbox = new VBox(20, label, hbox);
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    private VBox addDVD() {
        Label label = new Label("Ajouter un DVD");
        label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField titleField = new TextField();
        titleField.setMaxWidth(300);
        titleField.setStyle("-fx-font-size: 15px;");
        titleField.setPromptText("Titre");

        TextField stateField = new TextField();
        stateField.setMaxWidth(300);
        stateField.setStyle("-fx-font-size: 15px;");
        stateField.setPromptText("Etat");

        TextField publicationDateField = new TextField();
        publicationDateField.setMaxWidth(300);
        publicationDateField.setStyle("-fx-font-size: 15px;");
        publicationDateField.setPromptText("Date de publication");

        TextField durationField = new TextField();
        durationField.setMaxWidth(300);
        durationField.setStyle("-fx-font-size: 15px;");
        durationField.setPromptText("Durée");

        Button addButton = new Button("Ajouter");
        addButton.setStyle("-fx-font-size: 15px; -fx-background-color: #1ab2d1; -fx-text-fill: white;");
        addButton.setOnAction(e -> controller.addDVD(titleField.getText(), stateField.getText(), publicationDateField.getText(), durationField.getText()));
        
        HBox hbox = new HBox(10, titleField, stateField, publicationDateField, durationField, addButton);
        hbox.setAlignment(Pos.TOP_LEFT);
        VBox vbox = new VBox(20, label, hbox);
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    private VBox deleteDVD() {
        Label label = new Label("Supprimer un DVD");
        label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        
        TextField titleField = new TextField();
        titleField.setMaxWidth(300);
        titleField.setStyle("-fx-font-size: 15px;");
        titleField.setPromptText("Titre");

        Button deleteButton = new Button("Supprimer");
        deleteButton.setStyle("-fx-font-size: 15px; -fx-background-color: #1ab2d1; -fx-text-fill: white;");
        deleteButton.setOnAction(e -> controller.deleteDVD(titleField.getText()));
        
        HBox hbox = new HBox(10, titleField, deleteButton);
        hbox.setAlignment(Pos.TOP_LEFT);
        VBox vbox = new VBox(20, label, hbox);
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    private VBox addBluray() {
        Label label = new Label("Ajouter un Bluray");
        label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField titleField = new TextField();
        titleField.setMaxWidth(300);
        titleField.setStyle("-fx-font-size: 15px;");
        titleField.setPromptText("Titre");

        TextField stateField = new TextField();
        stateField.setMaxWidth(300);
        stateField.setStyle("-fx-font-size: 15px;");
        stateField.setPromptText("Etat");

        TextField publicationDateField = new TextField();
        publicationDateField.setMaxWidth(300);
        publicationDateField.setStyle("-fx-font-size: 15px;");
        publicationDateField.setPromptText("Date de publication");

        TextField is4KField = new TextField();
        is4KField.setMaxWidth(300);
        is4KField.setStyle("-fx-font-size: 15px;");
        is4KField.setPromptText("4K ?");

        TextField durationField = new TextField();
        durationField.setMaxWidth(300);
        durationField.setStyle("-fx-font-size: 15px;");
        durationField.setPromptText("Durée");

        Button addButton = new Button("Ajouter");
        addButton.setStyle("-fx-font-size: 15px; -fx-background-color: #1ab2d1; -fx-text-fill: white;");
        addButton.setOnAction(e -> controller.addBluray(titleField.getText(), stateField.getText(), publicationDateField.getText(), is4KField.getText(), durationField.getText()));

        HBox hbox = new HBox(10, titleField, stateField, publicationDateField, is4KField, durationField, addButton);
        hbox.setAlignment(Pos.TOP_LEFT);
        VBox vbox = new VBox(20, label, hbox);
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    private VBox deleteBluray() {
        Label label = new Label("Supprimer un Bluray");
        label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        
        TextField titleField = new TextField();
        titleField.setMaxWidth(300);
        titleField.setStyle("-fx-font-size: 15px;");
        titleField.setPromptText("Titre");

        Button deleteButton = new Button("Supprimer");
        deleteButton.setStyle("-fx-font-size: 15px; -fx-background-color: #1ab2d1; -fx-text-fill: white;");
        deleteButton.setOnAction(e -> controller.deleteBluray(titleField.getText()));
        
        HBox hbox = new HBox(10, titleField, deleteButton);
        hbox.setAlignment(Pos.TOP_LEFT);
        VBox vbox = new VBox(20, label, hbox);
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    public void showReservationsPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Réservations en cours");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showSuccessMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
