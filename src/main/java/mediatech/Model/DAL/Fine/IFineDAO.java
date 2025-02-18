package mediatech.Model.DAL.Fine;

import mediatech.Model.BL.Fine;

public interface IFineDAO {

    public boolean insertFine(Fine fine);
    
    public boolean close();
    
}
