package mediatech.Model.DAL.Reservation;

import mediatech.Model.BL.Reservation;
import mediatech.Model.DAL.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDAO {
    private Connection connection;
    private PreparedStatement insertReservation;
    private PreparedStatement updateBookAvailability;
    private PreparedStatement updateDVDAvailability;
    private PreparedStatement updateBlurayAvailability;
    private PreparedStatement selectReservations;

    public ReservationDAO(DBConnection dbConnection) {
        try {
            this.connection = dbConnection.getConnection();
            this.insertReservation = connection.prepareStatement("INSERT INTO reservation" +
                "(mediaType, id_media, id_user, reservationDate, expirationDate, active) VALUES (?, ?, ?, ?, ?, true)");
            this.updateBookAvailability = connection.prepareStatement("UPDATE book SET available = ? WHERE id = ?");
            this.updateDVDAvailability = connection.prepareStatement("UPDATE dvd SET available = ? WHERE id = ?");
            this.updateBlurayAvailability = connection.prepareStatement("UPDATE bluray SET available = ? WHERE id = ?");
            this.selectReservations = connection.prepareStatement("SELECT reservation.mediaType, reservation.id_media, users.id, " +
                "reservation.reservationDate, reservation.expirationDate, reservation.active FROM reservation reservation " + 
                "JOIN Users users ON reservation.id_user = users.id WHERE users.lastname = ? AND users.firstname = ? AND reservation.active = ?");
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

    public ArrayList<Reservation> selectReservations(String lastName, String firstName) {
        ArrayList<Reservation> reservationsList = new ArrayList<Reservation>();
        try {
            this.selectReservations.setString(1, lastName);
            this.selectReservations.setString(2, firstName);
            this.selectReservations.setBoolean(3, true);
            ResultSet set = this.selectReservations.executeQuery();
            while (set.next()) {
                Reservation reservation = new Reservation(set.getInt(2), set.getInt(3), set.getString(1), 
                    set.getDate(4), set.getDate(5), set.getBoolean(6));
                reservationsList.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationsList;
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
