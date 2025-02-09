package mediatech.Controller;

import mediatech.Model.DAL.DBConnection;
import mediatech.Model.DAL.User.UserDAO;
import mediatech.View.LoginView;

public class LoginController {
    private LoginView view;
    private UserDAO userDAO;
    private DBConnection dbConnection;

    public LoginController(LoginView view) {
        this.view = view;
        this.dbConnection = new DBConnection();
        this.userDAO = new UserDAO(dbConnection);
    }

    public void handleLogin(String email, String password) {
        if (userDAO.login(email, password)) {
            view.showLoginSuccess();
        } else {
            view.showLoginError();
        }
    }
}