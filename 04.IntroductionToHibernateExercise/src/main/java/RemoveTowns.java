import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String townName = scanner.nextLine();
        EntityManagerFactory factory= Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Town town = (Town) em.createQuery("SELECT t FROM Town t WHERE t.name Like ?")
                .setParameter(0,townName)
                .getSingleResult();
        List<Address> addresses = em.createQuery("SELECT a FROM Address a WHERE a.town.id = ?")
                .setParameter(0,town.getId())
                .getResultList();
        int count = addresses.size();
        em.detach(town);
        em.remove(town);
        for (Address address : addresses) {
            em.detach(address);
            em.remove(address);
        }
        System.out.println(count+ "address in "+townName+" deleted");



        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
