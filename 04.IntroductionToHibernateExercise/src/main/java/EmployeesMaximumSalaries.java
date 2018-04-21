import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        @SuppressWarnings("unchecked")
        List<Department> result = em
                .createQuery("SELECT d FROM Department d")
                .getResultList();
        System.out.println(result.size());
        for (Department department : result) {
            Employee max = department.getEmployees().stream().max(Comparator.comparing(Employee::getSalary)).get();
            System.out.println(max.getSalary());
            if(max.getSalary().compareTo(BigDecimal.valueOf(30000))<0
                    && max.getSalary().compareTo(BigDecimal.valueOf(70000)) >0) {
                System.out.printf("%s - %.2f",department.getName(),max.getSalary());
            }
        }


        em.getTransaction().commit();
    }
}
