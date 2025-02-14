package mediatech.Controller;

import mediatech.Model.BL.Book;
import mediatech.Model.BL.DVD;
import mediatech.Model.BL.Bluray;
import mediatech.Model.BL.MediaObject;
import mediatech.Model.BL.Reservation;
import mediatech.Model.DAL.Book.BookDAO;
import mediatech.Model.DAL.DVD.DVDDAO;
import mediatech.Model.DAL.DBConnection;
import mediatech.Model.DAL.Bluray.BlurayDAO;
import mediatech.Model.DAL.Reservation.ReservationDAO;
import mediatech.View.BookingView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BookingController {
    private BookingView view;
    private BookDAO bookDAO;
    private DVDDAO dvdDAO;
    private BlurayDAO blurayDAO;
    private ReservationDAO reservationDAO;
    private DBConnection dbConnection;

    public BookingController(BookingView view) {
        this.view = view;
        this.dbConnection = new DBConnection();
        this.bookDAO = new BookDAO(dbConnection);
        this.dvdDAO = new DVDDAO(dbConnection);
        this.blurayDAO = new BlurayDAO(dbConnection);
        this.reservationDAO = new ReservationDAO(dbConnection);
    }

    public ArrayList<Book> getAllBooks() {
        return bookDAO.getAllAvailableBooks();
    }

    public ArrayList<DVD> getAllDVDs() {
        return dvdDAO.getAllAvailableDVDs();
    }

    public ArrayList<Bluray> getAllBlurays() {
        return blurayDAO.getAllAvailableBlurays();
    }

    public void addMediaObject(MediaObject mediaObject) {
        if (mediaObject == null) {
            view.showErrorMessage("No media object selected.");
            return;
        }
    
        // Create a new reservation for the selected media object
        Reservation reservation = new Reservation();
        reservation.setMediaObject(mediaObject);
        reservation.setUser(view.getCurrentUser()); // Assuming there's a method to get the current user
    
        try {
            reservationDAO.create(reservation);
            view.showSuccessMessage("Reservation created successfully.");
        } catch (Exception e) {
            view.showErrorMessage("Failed to create reservation.");
            e.printStackTrace();
        }
    }
}
