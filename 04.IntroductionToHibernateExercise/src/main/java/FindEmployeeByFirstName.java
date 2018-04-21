package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class FindEmployeeByFirstName {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstNamePattern = scanner.nextLine();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em =factory.createEntityManager();
        em.getTransaction().begin();





        @SuppressWarnings("unchecked")
        List<Employee> result = em.createQuery("SELECT e FROM Employee e WHERE e.firstName LIKE ? ")
                .setParameter(0,firstNamePattern+"%")
                .getResultList();

        for (Employee employee : result) {
            System.out.printf("%s %s - %s - ($%.2f)\n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDepartment().getName(),
                    employee.getSalary());
        }

        em.getTransaction().commit();
    }
}
