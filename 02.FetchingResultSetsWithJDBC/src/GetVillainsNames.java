import connector.ConnectionClass;

import java.sql.*;

public class GetVillainsNames {
    public static void main(String[] args) throws SQLException {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        conn = ConnectionClass.getConnection();

        String SQL = "SELECT v.name, COUNT(mv.minion_id) as `sum`\n" +
                "FROM minions_to_villains as mv\n" +
                "JOIN villains as v\n" +
                "ON mv.villain_id = v.id\n" +
                "GROUP BY mv.villain_id\n" +
                "having `sum` >3\n" +
                "ORDER BY `sum` desc;";
        st = conn.createStatement();
        rs = st.executeQuery(SQL);
        while(rs.next()){
            System.out.printf("%s %s\n",rs.getString(1),rs.getString(2));

        }

    }
}
