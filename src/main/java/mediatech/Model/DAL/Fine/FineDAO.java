package mediatech.Model.DAL.Fine;

import mediatech.Model.BL.Fine;
import mediatech.Model.DAL.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FineDAO implements IFineDAO {
    private Connection connection;
    private PreparedStatement insertFine;
    private PreparedStatement createTable;

    public FineDAO(DBConnection dbConnection) {
        try {
            this.connection = dbConnection.getConnection();
            this.createTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS fine(id_fine SERIAL PRIMARY KEY, id_reservation INTEGER NOT NULL, "
                + "overduedays INTEGER NOT NULL, dailyamount NUMERIC NOT NULL, fineamount NUMERIC NOT NULL,"
                + "FOREIGN KEY (id_reservation) REFERENCES reservation(reservation_id));");
            try {
                this.createTable.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            this.insertFine = connection.prepareStatement("INSERT INTO fine (id_reservation, overduedays, dailyamount, fineAmount) VALUES (?, ?, ?, ?)");
        } catch (SQLException e) {
            e.printStackTrace();
          }
    }
    
    
    @Override
    public boolean insertFine(Fine fine) {
        try {
            insertFine.setInt(1, fine.getReservationId());
            insertFine.setInt(2, fine.getOverdueDays());
            insertFine.setDouble(3, fine.getDailyAmount());
            insertFine.setDouble(4, fine.getFineAmount());            
            insertFine.executeUpdate();
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
        if (this.insertFine != null) {
            try {
                this.insertFine.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        return returnValue;
    }
}
