package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class ContainsEmployee {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em =factory.createEntityManager();
        em.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String[] fullName = scanner.nextLine().split(" ");
        String firstName = fullName[0];
        String lastName = fullName[1];


        @SuppressWarnings("unchecked")
        List<Employee> result = em.createQuery("SELECT e FROM Employee e WHERE e.firstName like ? AND e.lastName like ?")
                .setParameter(0,firstName)
                .setParameter(1,lastName)
                .getResultList();

        if(result.size() ==0){
            System.out.println("No");
        }else{
            System.out.println("Yes");
        }

        em.getTransaction().commit();
    }
}
