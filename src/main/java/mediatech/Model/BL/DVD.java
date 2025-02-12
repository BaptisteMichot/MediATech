package mediatech.Model.BL;

import java.util.Date;

public class DVD extends MediaObject {

    private int duration;

    public DVD(int id, String title, boolean available, String state, Date publicationDate, int duration) {
        super(id, title, available, state, publicationDate);

        this.duration = duration;
    }

    public DVD() {}

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
}
