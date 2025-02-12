package mediatech.Model.DAL.Bluray;

import mediatech.Model.BL.Bluray;
import mediatech.Model.DAL.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BlurayDAO {
    private Connection connection;
    private PreparedStatement selectAvailableBlurays;
    private PreparedStatement selectBlurayByTitle;

    public BlurayDAO(DBConnection dbConnection) {
        try {
            this.connection = dbConnection.getConnection();
            this.selectAvailableBlurays = connection.prepareStatement("SELECT * FROM bluray WHERE available = true");
            this.selectBlurayByTitle = connection.prepareStatement("SELECT * FROM bluray WHERE title = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //@Override
    public ArrayList<Bluray> getAllAvailableBlurays() {
        ArrayList<Bluray> listBlurays = new ArrayList<Bluray>();
        try {
            ResultSet set = this.selectAvailableBlurays.executeQuery();
            while (set.next()) {
                Bluray bluray = new Bluray(set.getInt(1), set.getString(2), set.getBoolean(3), 
                    set.getString(4), set.getDate(5), set.getBoolean(6), set.getInt(7));
                listBlurays.add(bluray);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBlurays;
    }


    //@Override
    public Bluray getBlurayByTitle(String title) {
        try {
            this.selectBlurayByTitle.setString(1, title);
            ResultSet set = this.selectBlurayByTitle.executeQuery();

            if (set.next()) {
                Bluray bluray = new Bluray(set.getInt(1), set.getString(2), set.getBoolean(3), 
                set.getString(4), set.getDate(5), set.getBoolean(6), set.getInt(7));
                return bluray;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(title + " not found");
        return null;
    }


    // //@Override
    // public boolean create(Bluray bluray) {
    //     try {
    //         String query = "INSERT INTO bluray (title, duration) VALUES (?, ?)";
    //         PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    //         stmt.setString(1, bluray.getTitle());
    //         stmt.setInt(2, bluray.getDuration());

    //         int affectedRows = stmt.executeUpdate();
    //         if (affectedRows > 0) {
    //             ResultSet generatedKeys = stmt.getGeneratedKeys();
    //             if (generatedKeys.next()) {
    //                 bluray.setId(generatedKeys.getInt(1));
    //             }
    //             return true;
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return false;
    // }


    // //@Override
    // public boolean delete(int id) {
    //     try {
    //         String query = "DELETE FROM bluray WHERE id = ?";
    //         PreparedStatement stmt = connection.prepareStatement(query);
    //         stmt.setInt(1, id);

    //         return stmt.executeUpdate() > 0;
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return false;
    // }
}
