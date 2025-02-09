package mediatech.Model.DAL.User;

import mediatech.Model.BL.User;
import mediatech.Model.DAL.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection connection;
    private PreparedStatement insertUser;
    private PreparedStatement selectUser;    

    public UserDAO(DBConnection dbConnection) {
        try {
            this.connection = dbConnection.getConnection();
            this.insertUser = connection.prepareStatement("INSERT INTO users (firstName, lastName, email, password, role) VALUES (?, ?, ?, ?, ?)");
            this.selectUser = connection.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean register(User user) {
        try {
            insertUser.setString(1, user.getLastName());
            insertUser.setString(2, user.getFirstName());
            insertUser.setString(3, user.getEmail());
            insertUser.setString(4, user.getPassword());
            insertUser.setString(5, user.getRole());
            insertUser.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean login(String email, String password) {
        try {
            selectUser.setString(1, email);
            selectUser.setString(2, password);
            ResultSet set = this.selectUser.executeQuery();
            return set.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean close() {
        boolean returnValue = true;

        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }

        if (this.insertUser != null) {
            try {
                this.insertUser.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }

        if (this.selectUser != null) {
            try {
                this.selectUser.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }

        return returnValue;
    }
}