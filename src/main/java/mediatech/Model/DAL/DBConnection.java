package mediatech.Model.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://127.0.0.1/mediatechdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Q47!pL9s$2kT";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}