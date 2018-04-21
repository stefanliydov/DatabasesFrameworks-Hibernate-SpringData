
import connector.ConnectionClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PrintAllMinionsNames {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionClass.getConnection();

        PreparedStatement stmt = connection.prepareStatement("SELECT name FROM minions");
        ResultSet rs = stmt.executeQuery();

        List<String> names = new LinkedList<>();

        while(rs.next()){
            names.add(rs.getString("name"));
        }
        int startIndex = 0;
        int endIndex= names.size()-1;

        while(startIndex<endIndex){
            System.out.println(names.get(startIndex++));
            System.out.println(names.get(endIndex--));
        }

    }
}
