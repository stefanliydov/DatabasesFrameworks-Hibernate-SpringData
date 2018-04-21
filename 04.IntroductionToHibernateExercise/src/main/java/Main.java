import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        List<Town> townList = em.createQuery("SELECT t FROM Town t WHERE length(t.name) >5 ").getResultList();
        for (Town town : townList) {
           //em.detach(town);
           //town.setName(town.getName().toLowerCase());
           //em.merge(town);
            System.out.println(town.getName());
        }

        em.getTransaction().commit();
        em.close();

    }
}
