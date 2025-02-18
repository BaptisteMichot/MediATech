package mediatech.Model.DAL.User;

import mediatech.Model.BL.User;

public interface IUserDAO {
    
    public boolean register(User user);
    
    public boolean login(String email, String password);
    
    public boolean updatePassword(String email, String newPassword);
    
    public User getUser(String email, String password);
    
    public boolean close();
}
