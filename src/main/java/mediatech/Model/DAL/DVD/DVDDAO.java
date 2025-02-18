package mediatech.Model.DAL.DVD;

import mediatech.Model.BL.DVD;
import mediatech.Model.DAL.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DVDDAO implements IDVDDAO {
    private Connection connection;
    private PreparedStatement selectAvailableDVDs;
    private PreparedStatement selectDVDTitleById;
    private PreparedStatement selectDVDIdByTitle;
    private PreparedStatement updateDVDAvailability;
    private PreparedStatement updateDVDState;
    private PreparedStatement insertDVD;
    private PreparedStatement deleteDVD;

    public DVDDAO(DBConnection dbConnection) {
        try {
            this.connection = dbConnection.getConnection();
            this.selectAvailableDVDs = connection.prepareStatement("SELECT * FROM dvd WHERE available = true");
            this.selectDVDTitleById = connection.prepareStatement("SELECT title FROM dvd WHERE id = ?");
            this.selectDVDIdByTitle = connection.prepareStatement("SELECT id FROM dvd WHERE title = ?");
            this.updateDVDAvailability = connection.prepareStatement("UPDATE dvd SET available = ? WHERE id = ?");
            this.updateDVDState = connection.prepareStatement("UPDATE dvd SET state = ? WHERE id = ?");
            this.insertDVD = connection.prepareStatement("INSERT INTO dvd (title, available, state, publicationdate, duration) VALUES (?, ?, ?, ?, ?)");
            this.deleteDVD = connection.prepareStatement("DELETE FROM dvd WHERE title = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
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


    @Override
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


    @Override
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


    @Override
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


    @Override
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


    @Override
    public boolean insertDVD(String title, String state, Date publicationDate, int duration) {
        try {
            this.insertDVD.setString(1, title);
            this.insertDVD.setBoolean(2, true);
            this.insertDVD.setString(3, state);
            this.insertDVD.setDate(4, new java.sql.Date(publicationDate.getTime()));
            this.insertDVD.setInt(5, duration);
            this.insertDVD.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public boolean deleteDVD(String title) {
        try {
            this.deleteDVD.setString(1, title);
            this.deleteDVD.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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
        if (this.insertDVD != null) {
            try {
                this.insertDVD.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.deleteDVD != null) {
            try {
                this.deleteDVD.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        return returnValue;
    }
}
