package mediatech.Model.DAL.User;

public interface IUserDAO {
    
    public boolean close();

    public boolean insertUser(String firstName, String lastName, String email, String password, String role);
}
