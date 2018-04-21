import connector.ConnectionClass;

import java.sql.*;
import java.util.Scanner;

public class GetMinionsName {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int villainId = Integer.parseInt(scanner.nextLine());
        Connection conn = ConnectionClass.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT name\n" +
                "FROM villains\n" +
                "WHERE id = ?");
        stmt.setInt(1,villainId);
       ResultSet rs =  stmt.executeQuery();
       if(!rs.next()){
           System.out.println("No villain with ID "+villainId+" exists in the database.");
       }else {
           String villainName = rs.getString("name");
           System.out.println("Villain: " + villainName);

           stmt = conn.prepareStatement("SELECT minion_id FROM\n" +
                   "minions_to_villains\n" +
                   "WHERE villain_id = ?");
           stmt.setInt(1, villainId);
           rs = stmt.executeQuery();
           if (!rs.next()) {
               System.out.println("<no minions>");
           }
           int count =1;
           while(rs.next()){
               stmt = conn.prepareStatement("SELECT name,age FROM\n" +
                       "minions\n" +
                       "WHERE id = ?");
               stmt.setInt(1, rs.getInt(1));
               ResultSet currRs = stmt.executeQuery();
               currRs.next();
               String name =currRs.getString(1);
               int age = currRs.getInt("age");
               System.out.printf("%s. %s %s\n",count++,name, age);
           }
       }
    }
}
