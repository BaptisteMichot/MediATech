package mediatech.Model.DAL.Reservation;

import mediatech.Model.BL.Reservation;

import java.util.ArrayList;
import java.util.Date;

public interface IReservationDAO {

    public boolean addReservation(Reservation reservation);
    
    public ArrayList<Reservation> selectReservations(String lastName, String firstName);
    
    public boolean deleteReservation(String mediaType, int mediaId);
    
    public Date selectExpirationDateById(int reservationId);
    
    public boolean close();
    
}
