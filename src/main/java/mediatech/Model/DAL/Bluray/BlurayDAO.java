package mediatech.Model.DAL.Bluray;

import mediatech.Model.BL.Bluray;
import mediatech.Model.DAL.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BlurayDAO implements IBlurayDAO {
    private Connection connection;
    private PreparedStatement selectAvailableBlurays;
    private PreparedStatement selectBlurayTitleById;
    private PreparedStatement selectBlurayIdByTitle;
    private PreparedStatement updateBlurayAvailability;
    private PreparedStatement updateBlurayState;
    private PreparedStatement insertBluray;
    private PreparedStatement deleteBluray;

    public BlurayDAO(DBConnection dbConnection) {
        try {
            this.connection = dbConnection.getConnection();
            this.selectAvailableBlurays = connection.prepareStatement("SELECT * FROM bluray WHERE available = true");
            this.selectBlurayTitleById = connection.prepareStatement("SELECT title FROM bluray WHERE id = ?");
            this.selectBlurayIdByTitle = connection.prepareStatement("SELECT id FROM bluray WHERE title = ?");
            this.updateBlurayAvailability = connection.prepareStatement("UPDATE bluray SET available = ? WHERE id = ?");
            this.updateBlurayState = connection.prepareStatement("UPDATE bluray SET state = ? WHERE id = ?");
            this.insertBluray = connection.prepareStatement("INSERT INTO bluray (title, available, state, publicationdate, is4K, duration) VALUES (?, ?, ?, ?, ?, ?)");
            this.deleteBluray = connection.prepareStatement("DELETE FROM bluray WHERE title = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
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


    @Override
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


    @Override
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


    @Override
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


    @Override
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


    @Override
    public boolean insertBluray(String title, String state, Date publicationDate, boolean is4K, int duration) {
        try {
            this.insertBluray.setString(1, title);
            this.insertBluray.setBoolean(2, true);
            this.insertBluray.setString(3, state);
            this.insertBluray.setDate(4, new java.sql.Date(publicationDate.getTime()));
            this.insertBluray.setBoolean(5, is4K);
            this.insertBluray.setInt(6, duration);
            this.insertBluray.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public boolean deleteBluray(String title) {
        try {
            this.deleteBluray.setString(1, title);
            this.deleteBluray.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
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
        if (this.insertBluray != null) {
            try {
                this.insertBluray.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.deleteBluray != null) {
            try {
                this.deleteBluray.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        return returnValue;
    }
}
