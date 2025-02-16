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
    private PreparedStatement selectBlurayTitleById;
    private PreparedStatement selectBlurayIdByTitle;
    private PreparedStatement updateBlurayAvailability;
    private PreparedStatement updateBlurayState;

    public BlurayDAO(DBConnection dbConnection) {
        try {
            this.connection = dbConnection.getConnection();
            this.selectAvailableBlurays = connection.prepareStatement("SELECT * FROM bluray WHERE available = true");
            this.selectBlurayTitleById = connection.prepareStatement("SELECT title FROM bluray WHERE id = ?");
            this.selectBlurayIdByTitle = connection.prepareStatement("SELECT id FROM bluray WHERE title = ?");
            this.updateBlurayAvailability = connection.prepareStatement("UPDATE bluray SET available = ? WHERE id = ?");
            this.updateBlurayState = connection.prepareStatement("UPDATE bluray SET state = ? WHERE id = ?");
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


    public String getBlurayTitleById(int id) {
        String title = "";
        try {            
            this.selectBlurayTitleById.setInt(1, id);
            ResultSet set = this.selectBlurayTitleById.executeQuery();
            if (set.next()) {
                title = set.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return title;
    }

    public int getBlurayIdByTitle(String title) {
        int id = -1;
        try {            
            this.selectBlurayIdByTitle.setString(1, title);
            ResultSet set = this.selectBlurayIdByTitle.executeQuery();
            if (set.next()) {
                id = set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean updateBlurayAvailability(int id, boolean availability) {
        try {
            this.updateBlurayAvailability.setBoolean(1, availability);
            this.updateBlurayAvailability.setInt(2, id);
            this.updateBlurayAvailability.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBlurayState(int id, String state) {
        try {
            this.updateBlurayState.setString(1, state);
            this.updateBlurayState.setInt(2, id);
            this.updateBlurayState.executeUpdate();
            return true;
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
        if (this.selectAvailableBlurays != null) {
            try {
                this.selectAvailableBlurays.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.selectBlurayTitleById != null) {
            try {
                this.selectBlurayTitleById.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.selectBlurayIdByTitle != null) {
            try {
                this.selectBlurayIdByTitle.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.updateBlurayAvailability != null) {
            try {
                this.updateBlurayAvailability.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.updateBlurayState != null) {
            try {
                this.updateBlurayState.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        return returnValue;
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
