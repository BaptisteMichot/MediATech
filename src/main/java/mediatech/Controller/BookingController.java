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

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

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
        String mediaType;
        String message;
        int defaultDurationInMonths = 2;
        
        if (mediaObject == null) {
            view.showErrorMessage("Vous n'avez rien sélectionné");
            return;
        }

        //on détermine le type de MeidaObject sélectionné
        if (mediaObject instanceof Book) {
            mediaType = "book";
            message = "Le livre";
        } else if (mediaObject instanceof DVD) {
            mediaType = "dvd";
            message = "Le DVD";
        } else if (mediaObject instanceof Bluray) {
            mediaType = "bluray";
            message = "Le bluray";
        } else {
            view.showErrorMessage("Type de média non reconnu");
            return;
        }

        Date reservationDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(reservationDate);
        calendar.add(Calendar.MONTH, defaultDurationInMonths);
        Date expirationDate = calendar.getTime();


        Reservation reservation = new Reservation(mediaObject.getId(), view.getCurrentUser().getId(), 
            mediaType, reservationDate, expirationDate, true);

        if (reservationDAO.addReservation(reservation)) {
            view.showSuccessMessage(message + " a bien été réservé");
        } else {
            view.showErrorMessage("Vous avez déjà réservé ce média");
        }
    }
}
