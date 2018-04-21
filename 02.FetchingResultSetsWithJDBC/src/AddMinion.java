import connector.ConnectionClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AddMinion {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionClass.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        Scanner scanner = new Scanner(System.in);
        String[] firstLine = scanner.nextLine().split(" ");
        String[] secondLine = scanner.nextLine().split(" ");

        String minionName = firstLine[1];
        int minionAge = Integer.parseInt(firstLine[2]);
        String town = firstLine[3];
        String villainName = secondLine[1];
        int townId = -1;
        int villainId = -1;
        statement = connection.prepareStatement("Select * FROM minions WHERE name = ?");
        isMinionExisting(statement, minionName);

        townId = getTownIdAndAddTown(connection, town, statement, rs);
        villainId = getVillainIdAndAddVillain(connection, villainName, statement, rs);

        statement = connection.prepareStatement("INSERT minions(name, age, town) VALUES(?,?,?)");
        statement.setString(1, minionName);
        statement.setInt(2, minionAge);
        statement.setInt(3, townId);
        statement.execute();

        statement = connection.prepareStatement("SELECT id FROM minions WHERE name = ?");
        statement.setString(1, minionName);
        rs = statement.executeQuery();
        int minionId = -1;
        if (rs.next()) {
            minionId = rs.getInt("id");
        }

        statement = connection.prepareStatement("INSERT minions_to_villains(minion_id,villain_id) VALUES(?,?)");
        statement.setInt(1, minionId);
        statement.setInt(2, villainId);
        System.out.println("Successfully added " + minionName + " to be minion of " + villainName + ".");
    }

    private static int getVillainIdAndAddVillain(Connection connection, String villainName, PreparedStatement statement, ResultSet rs) throws SQLException {
        statement = connection.prepareStatement("SELECT id from villains\n" +
                "WHERE name = ?");
        statement.setString(1, villainName);
        rs = statement.executeQuery();
        if (!rs.next()) {
            try {
                PreparedStatement insertStatement = connection.prepareStatement("INSERT villains(name,evilness_factor) VALUES(?,'evil')");
                insertStatement.setString(1, villainName);
                insertStatement.execute();
                System.out.println("Villain " + villainName + " was added to the database.");
                rs = statement.executeQuery();
                rs.next();
            } catch (SQLException err) {
                System.out.println(err.getMessage());
            }
        }
        return rs.getInt("id");
    }

    private static int getTownIdAndAddTown(Connection connection, String town, PreparedStatement statement, ResultSet rs) throws SQLException {


        statement = connection.prepareStatement("SELECT id FROM town\n" +
                "WHERE name = ?");
        statement.setString(1, town);
        rs = statement.executeQuery();
        if (!rs.next()) {
            try {
                PreparedStatement insertStatement = connection.prepareStatement("INSERT town(name) VALUES(?)");
                insertStatement.setString(1, town);
                insertStatement.execute();
                System.out.println("Town " + town + " was added to the database.");
                rs = statement.executeQuery();
                rs.next();

            } catch (SQLException err) {
                System.out.println(err.getMessage());
            }
        }
        return rs.getInt("id");
    }

    private static void isMinionExisting(PreparedStatement statement, String minionName) throws SQLException {
        ResultSet rs;
        statement.setString(1, minionName);
        rs = statement.executeQuery();
        if (rs.next()) {
            throw new SQLException();
        }
    }
}
