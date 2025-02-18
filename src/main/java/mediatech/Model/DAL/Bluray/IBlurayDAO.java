package mediatech.Model.DAL.Bluray;

import java.util.ArrayList;
import java.util.Date;

import mediatech.Model.BL.Bluray;

public interface IBlurayDAO {
    
    public ArrayList<Bluray> getAllAvailableBlurays();
    
    public String getBlurayTitleById(int id);
    
    public int getBlurayIdByTitle(String title);
    
    public boolean updateBlurayAvailability(int id, boolean availability);
    
    public boolean updateBlurayState(int id, String state);
    
    public boolean insertBluray(String title, String state, Date publicationDate, boolean is4K, int duration);
    
    public boolean deleteBluray(String title);

    public boolean close();
}
