import java.sql.*;

public class InitialSetup {
    public static void main(String[] args) throws SQLException {
        final String URL = "jdbc:mysql://localhost:3306/MinionsDB?createDatabaseIfNotExist=true";
        final String USER = "root";
        final String PASSWORD = "stefan12";

        Connection conn = null;
        Statement st = null;
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String createTownTable = "CREATE TABLE town(\n" +
                "id INT primary key auto_increment,\n" +
                "`name` VARCHAR(50),\n" +
                "information VARCHAR(255)\n" +
                ")";
        String createMinionsTable = "CREATE TABLE minions(\n" +
                "id INT auto_increment PRIMARY KEY,\n" +
                "`name` VARCHAR(50),\n" +
                "age INT,\n"+
                "town INT,\n" +
                "constraint foreign key(town) references town(`id`)\n" +
                ")";
        String createVillainsTable = "CREATE TABLE villains(\n" +
                "id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "`name` VARCHAR(50),\n" +
                "evilness_factor enum('good','bad','evil','super evil')\n" +
                ")";
        String createMinionsToVillainsTable = "CREATE TABLE minions_to_villains(\n" +
                "minion_id INT,\n" +
                "villain_id INT,\n" +
                "constraint foreign key(minion_id) references minions(id),\n" +
                "constraint foreign key(villain_id) references villains(id)\n" +
                ")";
        String insertTowns = "INSERT town(`name`,`information`) VALUES(\"Sofia\",\"The capital city of Bulgaria\"),\n" +
                "(\"Plovdiv\",\"A big city in Bulgaria\"),\n" +
                "(\"Stara Zagora\",\"A quiet and beautiful city\"),\n" +
                "(\"Varna\",\"A city by the ocean\"),\n" +
                "(\"Burgas\",\"\")";
        String insertMinions = "INSERT minions(`name`,`age`,`town`)\n" +
                "VALUES(\"Blue\",9,1),\n" +
                "(\"Doodles\",12,2),\n" +
                "(\"Beedles\",7,3),\n" +
                "(\"Duffy\",18,4),\n" +
                "(\"John\",61,5)";
        String insertVillains = "INSERT villains(`name`,`evilness_factor`)\n" +
                "VALUES(\"Gru\",'good'),\n" +
                "(\"Dr.Octopus\",'bad'),\n" +
                "(\"Pennywise\",'super evil'),\n" +
                "(\"Steven\",'evil'),\n" +
                "(\"ScaryBadBoy99\",'good')";
        String insertMinionsToVillains="INSERT minions_to_villains(minion_id,villain_id)\n" +
                "VALUES(1,3),\n" +
                "(1,5),\n" +
                "(2,4),\n" +
                "(3,3),\n" +
                "(4,1),\n" +
                "(1,5),\n" +
                "(2,4),\n" +
                "(3,3),\n" +
                "(5,5),\n" +
                "(3,5),\n" +
                "(1,4),\n" +
                "(4,3),\n" +
                "(2,3),\n" +
                "(4,1)";

        st = conn.createStatement();
        st.executeUpdate(createTownTable);
        st.executeUpdate(createMinionsTable);
        st.executeUpdate(createVillainsTable);
        st.executeUpdate(createMinionsToVillainsTable);

        st.executeUpdate(insertTowns);
        st.executeUpdate(insertMinions);
        st.executeUpdate(insertVillains);
        st.executeUpdate(insertMinionsToVillains);

    }
}

