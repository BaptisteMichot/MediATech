package mediatech.Model.DAL.DVD;

import mediatech.Model.BL.DVD;
import mediatech.Model.DAL.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DVDDAO {
    private Connection connection;
    private PreparedStatement selectAvailableDVDs;
    private PreparedStatement selectDVDByTitle;

    public DVDDAO(DBConnection dbConnection) {
        try {
            this.connection = dbConnection.getConnection();
            this.selectAvailableDVDs = connection.prepareStatement("SELECT * FROM dvd WHERE available = true");
            this.selectDVDByTitle = connection.prepareStatement("SELECT * FROM dvd WHERE title = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //@Override
    public ArrayList<DVD> getAllAvailableDVDs() {
        ArrayList<DVD> listDVDs = new ArrayList<DVD>();
        try {
            ResultSet set = this.selectAvailableDVDs.executeQuery();
            while (set.next()) {
                DVD DVD = new DVD(set.getInt(1), set.getString(2), set.getBoolean(3), 
                    set.getString(4), set.getDate(5), set.getInt(6));
                listDVDs.add(DVD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDVDs;
    }


    //@Override
    public DVD getDVDByTitle(String title) {
        try {
            this.selectDVDByTitle.setString(1, title);
            ResultSet set = this.selectDVDByTitle.executeQuery();

            if (set.next()) {
                DVD DVD = new DVD(set.getInt(1), set.getString(2), set.getBoolean(3), 
                    set.getString(4), set.getDate(5), set.getInt(6));
                return DVD;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(title + " not found");
        return null;
    }


    // //@Override
    // public boolean create(DVD dvd) {
    //     try {
    //         String query = "INSERT INTO dvd (title, duration) VALUES (?, ?)";
    //         PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    //         stmt.setString(1, dvd.getTitle());
    //         stmt.setInt(2, dvd.getDuration());

    //         int affectedRows = stmt.executeUpdate();
    //         if (affectedRows > 0) {
    //             ResultSet generatedKeys = stmt.getGeneratedKeys();
    //             if (generatedKeys.next()) {
    //                 dvd.setId(generatedKeys.getInt(1));
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
    //         String query = "DELETE FROM dvd WHERE id = ?";
    //         PreparedStatement stmt = connection.prepareStatement(query);
    //         stmt.setInt(1, id);

    //         return stmt.executeUpdate() > 0;
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return false;
    // }
}
