package mediatech.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import mediatech.Controller.BookingController;
import mediatech.Model.BL.Book;
import mediatech.Model.BL.DVD;
import mediatech.Model.BL.Bluray;
import mediatech.Model.BL.User;


public class BookingView {
    private Stage stage;
    private User currentUser;
    private BookingController controller;

    private ComboBox<Book> bookComboBox;
    private ComboBox<DVD> dvdComboBox;
    private ComboBox<Bluray> blurayComboBox;

    public BookingView(Stage stage, User currentUser) {

        if (!"user".equals(currentUser.getRole())) {
            return;
        }

        this.stage = stage;
        this.currentUser = currentUser;
        this.controller = new BookingController(this);
        initView();
    }

    private void initView() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);
        
        Label title = new Label("Choisissez un média à réserver");
        title.setStyle("-fx-font-size: 45px; -fx-font-weight: bold;");

        layout.getChildren().addAll(title, createMediaSection("Livres", bookComboBox = new ComboBox<>()),
            createMediaSection("DVDs", dvdComboBox = new ComboBox<>()), 
            createMediaSection("Blurays", blurayComboBox = new ComboBox<>()));

        Scene scene = new Scene(layout, 600, 600);
        stage.setTitle("Réservation");
        stage.setScene(scene);
        stage.show();

        //fill comboboxes
        bookComboBox.getItems().addAll(controller.getAllBooks());
        dvdComboBox.getItems().addAll(controller.getAllDVDs());
        blurayComboBox.getItems().addAll(controller.getAllBlurays());
    }

    private VBox createMediaSection(String labelText, ComboBox<?> comboBox) {
        Label label = new Label(labelText);
        label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        comboBox.setPrefWidth(200);

        Button reserve = new Button("Réserver");
        reserve.setStyle("-fx-font-size: 20px; -fx-background-color: #1cb84c; -fx-text-fill: white;");
        Button details = new Button("Détails");
        details.setStyle("-fx-font-size: 20px; -fx-background-color: #1ab2d1; -fx-text-fill: white;");

        if (comboBox == bookComboBox) {
            reserve.setOnAction(e -> controller.addMediaObject(bookComboBox.getValue()));
            details.setOnAction(e -> controller.showMediaObjectDetails(bookComboBox.getValue()));
        }else if (comboBox == dvdComboBox) {
            reserve.setOnAction(e -> controller.addMediaObject(dvdComboBox.getValue()));
            details.setOnAction(e -> controller.showMediaObjectDetails(dvdComboBox.getValue()));
        }else if (comboBox == blurayComboBox) {
            reserve.setOnAction(e -> controller.addMediaObject(blurayComboBox.getValue()));
            details.setOnAction(e -> controller.showMediaObjectDetails(blurayComboBox.getValue()));
        } 

        HBox hbox = new HBox(10, comboBox, reserve, details);
        hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(10, label, hbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        
        return vbox;
    }

    public void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
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

    public void showDetailsMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Détails");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public User getCurrentUser() {
        return currentUser;
    }
}