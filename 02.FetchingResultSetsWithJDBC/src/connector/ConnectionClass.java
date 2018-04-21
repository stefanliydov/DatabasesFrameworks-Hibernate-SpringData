package connector;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

    public static java.sql.Connection getConnection() throws SQLException {
        final String URL = "jdbc:mysql://localhost:3306/MinionsDB?createDatabaseIfNotExist=true";
        final String USER = "root";
        final String PASSWORD = "stefan12";
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
