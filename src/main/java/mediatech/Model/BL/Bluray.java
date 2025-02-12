package mediatech.Model.BL;

import java.util.Date;

public class Bluray extends MediaObject {

    private boolean is4K;
    private int duration;

    public Bluray(int id, String title, boolean available, String state, Date publicationDate, boolean is4K, int duration) {
        super(id, title, available, state, publicationDate);

        this.is4K = is4K;
        this.duration = duration;
    }

    public Bluray() {}

    public boolean getIs4K() {
        return is4K;
    }
    public void setIs4K(boolean is4K) {
        this.is4K = is4K;
    }

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
}
