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
    private PreparedStatement selectDVDTitleById;
    private PreparedStatement selectDVDIdByTitle;
    private PreparedStatement updateDVDAvailability;
    private PreparedStatement updateDVDState;

    public DVDDAO(DBConnection dbConnection) {
        try {
            this.connection = dbConnection.getConnection();
            this.selectAvailableDVDs = connection.prepareStatement("SELECT * FROM dvd WHERE available = true");
            this.selectDVDTitleById = connection.prepareStatement("SELECT title FROM dvd WHERE id = ?");
            this.selectDVDIdByTitle = connection.prepareStatement("SELECT id FROM dvd WHERE title = ?");
            this.updateDVDAvailability = connection.prepareStatement("UPDATE dvd SET available = ? WHERE id = ?");
            this.updateDVDState = connection.prepareStatement("UPDATE dvd SET state = ? WHERE id = ?");
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


    public String getDVDTitleById(int id) {
        String title = "";
        try {            
            this.selectDVDTitleById.setInt(1, id);
            ResultSet set = this.selectDVDTitleById.executeQuery();
            if (set.next()) {
                title = set.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return title;
    }


    public int getDVDIdByTitle(String title) {
        int id = -1;
        try {            
            this.selectDVDIdByTitle.setString(1, title);
            ResultSet set = this.selectDVDIdByTitle.executeQuery();
            if (set.next()) {
                id = set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }


    public boolean updateDVDAvailability(int id, boolean availability) {
        try {
            this.updateDVDAvailability.setBoolean(1, availability);
            this.updateDVDAvailability.setInt(2, id);
            this.updateDVDAvailability.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean updateDVDState(int id, String state) {
        try {
            this.updateDVDState.setString(1, state);
            this.updateDVDState.setInt(2, id);
            this.updateDVDState.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;    
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
        if (this.selectAvailableDVDs != null) {
            try {
                this.selectAvailableDVDs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.selectDVDTitleById != null) {
            try {
                this.selectDVDTitleById.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.selectDVDIdByTitle != null) {
            try {
                this.selectDVDIdByTitle.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.updateDVDAvailability != null) {
            try {
                this.updateDVDAvailability.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.updateDVDState != null) {
            try {
                this.updateDVDState.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        return returnValue;
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
