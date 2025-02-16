package mediatech.Controller;

import mediatech.View.ManagementView;
import mediatech.Model.DAL.DBConnection;
import mediatech.Model.DAL.Reservation.ReservationDAO;
import mediatech.Model.DAL.Book.BookDAO;
import mediatech.Model.DAL.DVD.DVDDAO;
import mediatech.Model.DAL.Bluray.BlurayDAO;
import mediatech.Model.BL.Reservation;

import java.util.ArrayList;

public class ManagementController {
    private ManagementView view;
    private DBConnection dbConnection;
    private ReservationDAO reservationDAO;
    private BookDAO bookDAO;
    private DVDDAO dvdDAO;
    private BlurayDAO blurayDAO;

    public ManagementController(ManagementView view) {
        this.view = view;
        this.dbConnection = new DBConnection();
        this.reservationDAO = new ReservationDAO(dbConnection);
        this.bookDAO = new BookDAO(dbConnection);
        this.dvdDAO = new DVDDAO(dbConnection);
        this.blurayDAO = new BlurayDAO(dbConnection);
    }

    public void showReservations(String lastName, String firstName) {
        String message = "";
        ArrayList<Reservation> reservations = this.reservationDAO.selectReservations(lastName, firstName);
        if (!reservations.isEmpty()) {

            for (Reservation reservation : reservations) {
                String title;
                String mediaType;
                switch (reservation.getMediaType()) {
                    case "book":
                        title = this.bookDAO.getBookTitleById(reservation.getIdMedia());
                        mediaType = "Livre";
                        break;
                    case "dvd":
                        title = this.dvdDAO.getDVDTitleById(reservation.getIdMedia());
                        mediaType = "DVD";
                        break;
                    case "bluray":
                        title = this.blurayDAO.getBlurayTitleById(reservation.getIdMedia());
                        mediaType = "Bluray";
                        break;
                    default:
                        title = "Titre non trouvé";
                        mediaType = "Type non trouvé";
                        break;
                }
                message += "Type de média: " + mediaType + "\nTitre: " + title + 
                            "\nDate de début de réservation: " + reservation.getReservationDate() + 
                            "\nDate de fin de réservation: " + reservation.getExpirationDate() + "\n\n";
            }
            view.showReservationsPopup(message);
            
        } else {
            view.showErrorMessage("Cette personne n'a pas de réservation en cours");
        }
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
