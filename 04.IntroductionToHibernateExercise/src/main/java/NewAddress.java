import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class NewAddress {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String lastName = scanner.nextLine();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Address newAddress = new Address();
        newAddress.setText("Vitoshka 15");
        Employee result = (Employee) em.createQuery("SELECT e FROM Employee e WHERE e.lastName =?")
                .setParameter(0,lastName).setMaxResults(1).getSingleResult();
        result.setAddress(newAddress);


        em.persist(newAddress);
        em.getTransaction().commit();
    }
}
