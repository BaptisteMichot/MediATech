package mediatech.Controller;

import mediatech.View.ManagementView;
import mediatech.Model.DAL.DBConnection;
import mediatech.Model.DAL.Reservation.ReservationDAO;
import mediatech.Model.DAL.Book.BookDAO;
import mediatech.Model.DAL.DVD.DVDDAO;
import mediatech.Model.DAL.Fine.FineDAO;
import mediatech.Model.DAL.Bluray.BlurayDAO;
import mediatech.Model.BL.Reservation;
import mediatech.Model.BL.Fine;

import java.util.ArrayList;
import java.util.Date;

public class ManagementController {
    private ManagementView view;
    private DBConnection dbConnection;
    private ReservationDAO reservationDAO;
    private BookDAO bookDAO;
    private DVDDAO dvdDAO;
    private BlurayDAO blurayDAO;
    private FineDAO fineDAO;

    public ManagementController(ManagementView view) {
        this.view = view;
        this.dbConnection = new DBConnection();
        this.reservationDAO = new ReservationDAO(dbConnection);
        this.bookDAO = new BookDAO(dbConnection);
        this.dvdDAO = new DVDDAO(dbConnection);
        this.blurayDAO = new BlurayDAO(dbConnection);
        this.fineDAO = new FineDAO(dbConnection);
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

    public void restocking(String mediaType, String title, String state) {
        int mediaId = -1;
        boolean updateSuccessful = false;
        String message ="";

        switch (mediaType) {
            case "book":
                mediaId = this.bookDAO.getBookIdByTitle(title);
                if (mediaId != -1) {
                    this.bookDAO.updateBookState(mediaId, state);
                    this.bookDAO.updateBookAvailability(mediaId, true);
                    updateSuccessful = true;
                    message = "livre";
                }
                break;
            case "dvd":
                mediaId = this.dvdDAO.getDVDIdByTitle(title);
                if (mediaId != -1) {
                    this.dvdDAO.updateDVDState(mediaId, state);
                    this.dvdDAO.updateDVDAvailability(mediaId, true);
                    updateSuccessful = true;
                    message = "DVD";
                }
                break;
            case "bluray":
                mediaId = this.blurayDAO.getBlurayIdByTitle(title);
                if (mediaId != -1) {
                    this.blurayDAO.updateBlurayState(mediaId, state);
                    this.blurayDAO.updateBlurayAvailability(mediaId, true);
                    updateSuccessful = true;
                    message = "bluray";
                }
                break;
            default:
                view.showErrorMessage("Média non reconnu");
                return;
        }
    
        if (updateSuccessful) {
            this.reservationDAO.deleteReservation(mediaType, mediaId);
            view.showSuccessMessage("Le " + message + " " + title + " a été remis en stock avec succès");
        } else {
            view.showErrorMessage("Média non reconnu");
        }


    }

    public void checkFine(int reservationId) {
        try {
            double dailyAmount = 10.0;
            Date expirationDate = reservationDAO.selectExpirationDateById(reservationId);
            Fine fine = new Fine();
            fine.setReservationId(reservationId);
            fine.setOverdueDays(fine.calculateNbOverdueDays(expirationDate));
            fine.setDailyAmount(dailyAmount);
            fine.setFineAmount(fine.calculateFineAmount(fine.getOverdueDays(), dailyAmount));
            
            if (fine.getOverdueDays() > 0) {
                if (fineDAO.insertFine(fine)) {
                    view.showSuccessMessage("Amende pour la réservation " + reservationId + ": " + fine.getFineAmount() + " euros");
                } else {
                    view.showErrorMessage("Erreur lors de l'application de l'amende pour la réservation " + reservationId);
                }
            } else {
                view.showErrorMessage("Il n'y a aucun jour de retard pour la réservation " + reservationId);
            }
        } catch (Exception e) {
            view.showErrorMessage("Il n'y a pas d'amende à payer pour la réservation " + reservationId);
        }
    }

    public void addBook(String title, String state, Date publicationDate, String isbn, String author, String publisher, int pageCount) {
        try {
            if (this.bookDAO.insertBook(title, state, publicationDate, isbn, author, publisher, pageCount)) {
                view.showSuccessMessage("Le livre " + title + " a été ajouté avec succès");
            } else {
                view.showErrorMessage("Erreur lors de l'ajout du livre");
            }
        } catch (Exception e) {
            view.showErrorMessage("Erreur lors de l'ajout du livre");
        }
    }

    public void deleteBook(String title) {
        try {
            if (this.bookDAO.deleteBook(title)) {
                view.showSuccessMessage("Le livre " + title + " a été supprimé avec succès");
            } else {
                view.showErrorMessage("Erreur lors de la suppression du livre");
            }
        } catch (Exception e) {
            view.showErrorMessage("Erreur lors de la suppression du livre");
        }
    }

    public void addDVD(String title, String state, Date publicationDate, int duration) {
        try {
            if (this.dvdDAO.insertDVD(title, state, publicationDate, duration)) {
                view.showSuccessMessage("Le DVD " + title + " a été ajouté avec succès");
            } else {
                view.showErrorMessage("Erreur lors de l'ajout du DVD");
            }
        } catch (Exception e) {
            view.showErrorMessage("Erreur lors de l'ajout du DVD");
        }
    }

    public void deleteDVD(String title) {
        try {
            if (this.dvdDAO.deleteDVD(title)) {
                view.showSuccessMessage("Le DVD " + title + " a été supprimé avec succès");
            } else {
                view.showErrorMessage("Erreur lors de la suppression du DVD");
            }
        } catch (Exception e) {
            view.showErrorMessage("Erreur lors de la suppression du DVD");
        }
    }

    public void addBluray(String title, String state, Date publicationDate, boolean is4K, int duration) {
        try {
            if (this.blurayDAO.insertBluray(title, state, publicationDate, is4K, duration)) {
                view.showSuccessMessage("Le Bluray " + title + " a été ajouté avec succès");
            } else {
                view.showErrorMessage("Erreur lors de l'ajout du Bluray");
            }
        } catch (Exception e) {
            view.showErrorMessage("Erreur lors de l'ajout du Bluray");
        }
    }

    public void deleteBluray(String title) {
        try {
            if (this.blurayDAO.deleteBluray(title)) {
                view.showSuccessMessage("Le Bluray " + title + " a été supprimé avec succès");
            } else {
                view.showErrorMessage("Erreur lors de la suppression du Bluray");
            }
        } catch (Exception e) {
            view.showErrorMessage("Erreur lors de la suppression du Bluray");
        }
    }
}
