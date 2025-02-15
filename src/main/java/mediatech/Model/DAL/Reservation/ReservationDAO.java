package mediatech.Model.DAL.Reservation;

import mediatech.Model.BL.Reservation;
import mediatech.Model.DAL.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservationDAO {
    private Connection connection;
    private PreparedStatement insertReservation;
    private PreparedStatement updateBookAvailability;
    private PreparedStatement updateDVDAvailability;
    private PreparedStatement updateBlurayAvailability;

    public ReservationDAO(DBConnection dbConnection) {
        try {
            this.connection = dbConnection.getConnection();
            this.insertReservation = connection.prepareStatement("INSERT INTO reservation" +
                "(mediaType, id_media, id_user, reservationDate, expirationDate, active) VALUES (?, ?, ?, ?, ?, true)");
            this.updateBookAvailability = connection.prepareStatement("UPDATE book SET available = ? WHERE id = ?");
            this.updateDVDAvailability = connection.prepareStatement("UPDATE dvd SET available = ? WHERE id = ?");
            this.updateBlurayAvailability = connection.prepareStatement("UPDATE bluray SET available = ? WHERE id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addReservation(Reservation reservation) {
        try {
            insertReservation.setString(1, reservation.getMediaType());
            insertReservation.setInt(2, reservation.getIdMedia());
            insertReservation.setInt(3, reservation.getIdUser());
            insertReservation.setDate(4, new java.sql.Date(reservation.getReservationDate().getTime()));
            insertReservation.setDate(5, new java.sql.Date(reservation.getExpirationDate().getTime()));
            insertReservation.executeUpdate();

            switch (reservation.getMediaType()) {
                case "book":
                    updateBookAvailability.setBoolean(1, false);
                    updateBookAvailability.setInt(2, reservation.getIdMedia());
                    updateBookAvailability.executeUpdate();
                    break;
                case "dvd":
                    updateDVDAvailability.setBoolean(1, false);
                    updateDVDAvailability.setInt(2, reservation.getIdMedia());
                    updateDVDAvailability.executeUpdate();
                    break;
                case "bluray":
                    updateBlurayAvailability.setBoolean(1, false);
                    updateBlurayAvailability.setInt(2, reservation.getIdMedia());
                    updateBlurayAvailability.executeUpdate();
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean close() {
        boolean returnValue = true;

        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.insertReservation != null) {
            try {
                this.insertReservation.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.updateBookAvailability != null) {
            try {
                this.updateBookAvailability.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.updateDVDAvailability != null) {
            try {
                this.updateDVDAvailability.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.updateBlurayAvailability != null) {
            try {
                this.updateBlurayAvailability.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        return returnValue;
    }
}
