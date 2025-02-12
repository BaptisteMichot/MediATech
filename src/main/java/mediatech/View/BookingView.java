package mediatech.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mediatech.Controller.BookingController;
import mediatech.Model.BL.Book;
import mediatech.Model.BL.DVD;
import mediatech.Model.BL.User;
import mediatech.Model.BL.Bluray;

import java.util.ArrayList;

public class BookingView {
    private Stage stage;
    private BookingController controller;

    private ComboBox<Book> bookComboBox;
    private ComboBox<DVD> dvdComboBox;
    private ComboBox<Bluray> blurayComboBox;

    private Button bookAddButton;
    private Button dvdAddButton;
    private Button blurayAddButton;

    private User currentUser;

    public BookingView(Stage stage) {
        this.stage = stage;
        this.controller = new BookingController(this);
        initView();
    }

    private void initView() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);

        // Book Dropdown
        bookComboBox = new ComboBox<>();
        bookAddButton = new Button("Ajouter");
        bookAddButton.setOnAction(e -> controller.addMediaObject(bookComboBox.getValue()));
        VBox bookSection = new VBox(5, bookComboBox, bookAddButton);

        // DVD Dropdown
        dvdComboBox = new ComboBox<>();
        dvdAddButton = new Button("Ajouter");
        dvdAddButton.setOnAction(e -> controller.addMediaObject(dvdComboBox.getValue()));
        VBox dvdSection = new VBox(5, dvdComboBox, dvdAddButton);

        // Bluray Dropdown
        blurayComboBox = new ComboBox<>();
        blurayAddButton = new Button("Ajouter");
        blurayAddButton.setOnAction(e -> controller.addMediaObject(blurayComboBox.getValue()));
        VBox bluraySection = new VBox(5, blurayComboBox, blurayAddButton);

        layout.getChildren().addAll(bookSection, dvdSection, bluraySection);

        Scene scene = new Scene(layout, 600, 600);
        stage.setTitle("MediaTech - Réservation");
        stage.setScene(scene);
        stage.show();

        // Load media objects
        loadMediaObjects();
    }

    private void loadMediaObjects() {
        ArrayList<Book> books = controller.getAllBooks();
        ArrayList<DVD> dvds = controller.getAllDVDs();
        ArrayList<Bluray> blurays = controller.getAllBlurays();

        bookComboBox.getItems().addAll(books);
        dvdComboBox.getItems().addAll(dvds);
        blurayComboBox.getItems().addAll(blurays);
    }

    public void showErrorMessage(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showSuccessMessage(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
