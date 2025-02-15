package mediatech.Model.BL;

import java.util.Date;

public class Reservation {
    private int idMedia;
    private int idUser;
    private String mediaType;
    private Date reservationDate;
    private Date expirationDate;
    private boolean active;


    public Reservation(int idMedia, int idUser, String mediaType, Date reservationDate, Date expirationDate, boolean active) {
        this.idMedia = idMedia;
        this.idUser = idUser;
        this.mediaType = mediaType;
        this.reservationDate = reservationDate;
        this.expirationDate = expirationDate;
        this.active = active;
    }

    public Reservation() {}

    
    public int getIdMedia() {
        return idMedia;
    }
    public void setIdMedia(int idMedia) {
        this.idMedia = idMedia;
    }

    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getMediaType() {
        return mediaType;
    }
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Date getReservationDate() {
        return reservationDate;
    }
    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean getActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
