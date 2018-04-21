import java.sql.*;

public class ConnectionAndConnectionProperties {
    public static void main(String[] args) {

        final String URL = "jdbc:mysql://localhost:3306/soft_uni?createDatabaseIfNotExists=true";
        final String USER = "root";
        final String PASSWORD = "stefan12";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String SQL = "CREATE TABLE IF NOT EXISTS admins(" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "user VARCHAR(25)," +
                "password VARCHAR(20)" +
                ")";
        String insert = "INSERT INTO admins(user,password) " +
                "VALUES('Gerry','123443211')," +
                "('Stefan','stefan12')";
        String selectSQL = "SELECT * FROM admins";

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            if (conn != null) {
                System.out.println("Connection made!");
            }
            assert conn != null;
             stmt = conn.createStatement();

            stmt.executeUpdate(SQL);
            //stmt.executeUpdate(insert);
            rs = stmt.executeQuery(selectSQL);
            if(rs!=null){
                while (rs.next()){
                    System.out.printf("User: %s, Password: %s\n",
                            rs.getString("user"),
                            rs.getString("password"));
                }
            }

        } catch (SQLException error) {
            System.out.println("Connection failed: " + error.getMessage());
        } finally {
           if (rs != null) {
               try {
                   rs.close();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                conn = null;
                System.out.println("Connection closed!");
            }
        }

    }
}
