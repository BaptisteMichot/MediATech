package mediatech.Model.DAL.DVD;

import mediatech.Model.BL.DVD;

import java.util.ArrayList;
import java.util.Date;

public interface IDVDDAO {

    public ArrayList<DVD> getAllAvailableDVDs();
    
    public String getDVDTitleById(int id);
    
    public int getDVDIdByTitle(String title);
    
    public boolean updateDVDAvailability(int id, boolean availability);
    
    public boolean updateDVDState(int id, String state);
    
    public boolean insertDVD(String title, String state, Date publicationDate, int duration);
    
    public boolean deleteDVD(String title);

    public boolean close();    
}
