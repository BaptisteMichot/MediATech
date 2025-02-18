package mediatech.Model.DAL.Reservation;

import mediatech.Model.BL.Reservation;
import mediatech.Model.DAL.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ReservationDAO {
    private Connection connection;
    private PreparedStatement insertReservation;
    private PreparedStatement selectReservations;
    private PreparedStatement deleteReservation;
    private PreparedStatement selectExpirationDateById;

    public ReservationDAO(DBConnection dbConnection) {
        try {
            this.connection = dbConnection.getConnection();

            this.insertReservation = connection.prepareStatement("INSERT INTO reservation" +
                "(mediaType, id_media, id_user, reservationDate, expirationDate, active) VALUES (?, ?, ?, ?, ?, true)");
            this.selectReservations = connection.prepareStatement("SELECT reservation.mediaType, reservation.id_media, users.id, " +
                "reservation.reservationDate, reservation.expirationDate, reservation.active, reservation.reservation_id FROM reservation reservation " + 
                "JOIN Users users ON reservation.id_user = users.id WHERE users.lastname = ? AND users.firstname = ? AND reservation.active = ?");
            this.deleteReservation = connection.prepareStatement("DELETE FROM reservation WHERE mediaType = ? AND id_media = ?");
            this.selectExpirationDateById = connection.prepareStatement("SELECT expirationDate FROM reservation WHERE reservation_id = ?");
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
                    set.getDate(4), set.getDate(5), set.getBoolean(6), set.getInt(7));
                reservationsList.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationsList;
    }

    public boolean deleteReservation(String mediaType, int mediaId) {
        try {
            this.deleteReservation.setString(1, mediaType);
            this.deleteReservation.setInt(2, mediaId);
            this.deleteReservation.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Date selectExpirationDateById(int reservationId) {
        Date expirationDate = null;
        try {
            this.selectExpirationDateById.setInt(1, reservationId);
            ResultSet set = this.selectExpirationDateById.executeQuery();
            if (set.next()) {
                expirationDate = set.getDate(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expirationDate;
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
        if (this.selectReservations != null) {
            try {
                this.selectReservations.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.deleteReservation != null) {
            try {
                this.deleteReservation.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.selectExpirationDateById != null) {
            try {
                this.selectExpirationDateById.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        return returnValue;
    }
}
