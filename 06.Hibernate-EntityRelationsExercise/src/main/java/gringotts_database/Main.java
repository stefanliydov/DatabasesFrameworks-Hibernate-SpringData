package gringotts_database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("gringotts");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        WizardDeposit wizardDeposit = new WizardDeposit();
        em.persist(wizardDeposit);

        em.getTransaction().commit();
    }
}
