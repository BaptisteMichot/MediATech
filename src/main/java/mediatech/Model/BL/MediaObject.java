package mediatech.Model.BL;

import java.util.Date;

public class MediaObject {

    private int id;
    private String title;
    private boolean available;
    private String state;
    private Date publicationDate;

    public MediaObject(int id, String title, boolean available, String state, Date publicationDate) {
        this.id = id;
        this.title = title;
        this.available = available;
        this.state = state;
        this.publicationDate = publicationDate;
    }    

    public MediaObject() {}

    public int getId() { 
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }
    public String getTitle() { 
        return title; 
    }
    public void setTitle(String title) { 
        this.title = title; 
    }
    public boolean isAvailable() { 
        return available; 
    }
    public void setAvailable(boolean available) { 
        this.available = available; 
    }
    public String getState() { 
        return state; 
    }
    public void setState(String state) { 
        this.state = state; 
    }
    public Date getPublicationDate() { 
        return publicationDate; 
    }
    public void setPublicationDate(Date publicationDate) { 
        this.publicationDate = publicationDate;
    }


    //display title in comboboxes
    @Override
    public String toString() {
        return this.title;
    }
}
