package mediatech.Controller;

import mediatech.Model.BL.User;
import mediatech.Model.DAL.DBConnection;
import mediatech.Model.DAL.User.IUserDAO;
import mediatech.Model.DAL.User.UserDAO;
import mediatech.View.LoginView;

public class LoginController {
    private LoginView view;
    private IUserDAO userDAO;
    private User currentUser;
    private DBConnection dbConnection;

    public LoginController(LoginView view) {
        this.view = view;
        this.dbConnection = new DBConnection();
        this.userDAO = new UserDAO(dbConnection);
    }

    public void handleLogin(String email, String password) {
        if (userDAO.login(email, password)) {
            this.currentUser = userDAO.getUser(email, password);
            view.showLoginSuccess();
            if (currentUser.getRole().equals("user")) {
                view.openBookingView();
            } else if (currentUser.getRole().equals("employee")) {
                view.openManagementView();
            }
        } else {
            view.showLoginError();
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }
}