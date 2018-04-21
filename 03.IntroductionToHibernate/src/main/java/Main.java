import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.intellij.lang.annotations.Language;


import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        //    final String URL = "jdbc:mysql://localhost:3306/MinionsDB?createDatabaseIfNotExist=true";
        //    final String USER = "root";
        //    final String PASSWORD = "stefan12";
        //    DriverManager.getConnection(URL, USER, PASSWORD);
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory =
                cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //language=SQL

      //  session.createQuery("SELECT * FROM students");
        // Your Code Here
        session.getTransaction().commit();
        session.close();


    }
}
