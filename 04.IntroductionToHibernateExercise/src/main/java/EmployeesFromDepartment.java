import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesFromDepartment {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        List<Employee> result = em.createQuery("Select e FROM Employee e WHERE e.department.name like 'Research and Development'" +
                "ORDER BY  e.salary ASC, e.id ASC ").getResultList();
        for (Employee employee : result) {
            System.out.printf("%s %s from %s - $%.2f\n",employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDepartment().getName(),
                    employee.getSalary());
        }

        em.getTransaction().commit();

    }
}
