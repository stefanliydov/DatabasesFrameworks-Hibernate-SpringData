import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class IncreaseSalaries {
    public static void main(String[] args) {
        EntityManagerFactory factory= Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        List<Employee> result =
                em.createQuery("SELECT e " +
                        "FROM Employee e " +
                        "WHERE e.department.name IN('Engineering','Tool Design','Marketing','Information Services')")
                .getResultList();
        BigDecimal increasePercent = BigDecimal.valueOf(0.12);
        for (Employee employee : result) {
            em.detach(employee);
            employee.setSalary(employee.getSalary().add(employee.getSalary().multiply(increasePercent)));
            em.merge(employee);
        }

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
