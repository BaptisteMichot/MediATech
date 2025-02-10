package mediatech.Controller;

import mediatech.Model.BL.User;
import mediatech.Model.DAL.DBConnection;
import mediatech.Model.DAL.User.UserDAO;
import mediatech.View.RegisterView;

public class RegisterController {
    private RegisterView view;
    private UserDAO userDAO;
    private DBConnection dbConnection;

    public RegisterController(RegisterView view) {
        this.view = view;
        this.dbConnection = new DBConnection();
        this.userDAO = new UserDAO(dbConnection);
    }

    public void handleRegister(String nom, String prenom, String email, String password) {
        final String ROLE = "user";
        User newUser = new User(nom, prenom, email, password, ROLE);
        boolean[] invalidFields = new boolean[4];
        if (userDAO.register(newUser)) {
            view.showRegistrationSuccess();
        } else {
            invalidFields[0] = !newUser.isValidName(nom);
            invalidFields[1] = !newUser.isValidName(prenom);
            invalidFields[2] = !newUser.isValidEmail(email);
            invalidFields[3] = !newUser.isValidPassword(password);
            
            view.showRegistrationError(invalidFields);
        }
    }
}