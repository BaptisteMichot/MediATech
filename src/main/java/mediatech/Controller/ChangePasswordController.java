package mediatech.Controller;

import mediatech.Model.BL.User;
import mediatech.Model.DAL.User.UserDAO;
import mediatech.Model.DAL.DBConnection;
import mediatech.View.ChangePasswordView;

public class ChangePasswordController {
    private ChangePasswordView view;
    private UserDAO userDAO;
    private User user;
    private DBConnection dbConnection;

    public ChangePasswordController(ChangePasswordView view) {
        this.view = view;
        this.dbConnection = new DBConnection();
        this.userDAO = new UserDAO(dbConnection);
        this.user = new User();
    }

    public void handleChangePassword(String email, String currentPassword, String newPassword) {
        if (!userDAO.login(email, currentPassword)) {
            view.showChangePasswordError("errorLogin");
            return;
        }
        if (!user.changePassword(currentPassword, newPassword)) {
            view.showChangePasswordError("errorNewPassword");
            return;
        }

        if (userDAO.updatePassword(email, newPassword)) {
            view.showChangePasswordSuccess();
        } else {
            view.showChangePasswordError("error");
        }
    }
}