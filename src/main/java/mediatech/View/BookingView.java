package mediatech.View;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mediatech.Controller.BookingController;
import mediatech.Model.BL.Book;
import mediatech.Model.BL.DVD;
import mediatech.Model.BL.Bluray;
import mediatech.Model.BL.MediaObject;

import java.util.List;

public class BookingView {
    private Stage stage;
    private BookingController controller;

    private ComboBox<Book> bookComboBox;
    private ComboBox<DVD> dvdComboBox;
    private ComboBox<Bluray> blurayComboBox;

    private Button bookAddButton;
    private Button dvdAddButton;
    private Button blurayAddButton;

    public BookingView(Stage stage) {
        this.stage = stage;
        this.controller = new BookingController(this);
        initView();
    }

    private void initView() {
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 20px;");

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

        root.getChildren().addAll(bookSection, dvdSection, bluraySection);

        // Load media objects
        loadMediaObjects();
    }

    private void loadMediaObjects() {
        List<Book> books = controller.getAllBooks();
        List<DVD> dvds = controller.getAllDVDs();
        List<Bluray> blurays = controller.getAllBlurays();

        bookComboBox.getItems().addAll(books);
        dvdComboBox.getItems().addAll(dvds);
        blurayComboBox.getItems().addAll(blurays);
    }

    public void showBookingView() {
        // TODO: Implement scene setup and show
    }
}
