package mediatech.Controller;

import mediatech.View.ManagementView;
import mediatech.Model.DAL.DBConnection;

public class ManagementController {
    private ManagementView view;
    private DBConnection dbConnection;

    public ManagementController(ManagementView view) {
        this.view = view;
        this.dbConnection = new DBConnection();

    }

    public void showReservations(String lastName, String firstName) {

    }

    public void endOfReservation(String lastName, String firstName, String mediaType, String title) {

    }

    public void restocking(String mediaType, String title, String state) {

    }

    public void addBook(String title, String state, String publicationDate, String isbn, String author, String publisher, String pageCount) {

    }

    public void deleteBook(String title) {

    }

    public void addDVD(String title, String state, String publicationDate, String duration) {

    }

    public void deleteDVD(String title) {

    }

    public void addBluray(String title, String state, String publicationDate, String is4K, String duration) {

    }

    public void deleteBluray(String title) {

    }
}
