import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesWithSalaryOver50000 {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        List<Employee> result= em.createQuery("Select e FROM Employee e Where e.salary>50000").getResultList();
        for (Employee employee : result) {
            System.out.println(employee.getFirstName());
        }



        em.getTransaction().commit();

    }
}
