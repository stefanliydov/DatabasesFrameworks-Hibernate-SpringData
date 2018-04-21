import entities.Address;
import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GetEmployeeWithProject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int employeeId = Integer.parseInt(scanner.nextLine());

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Employee found = (Employee) em.createQuery("Select e From Employee e Where e.id = ?")
                .setParameter(0,employeeId)
                .getSingleResult();

        List<Project> projects = found.getProjects().stream().sorted(Comparator.comparing(Project::getName)).collect(Collectors.toList());
        System.out.printf("%s %s - %s\n",
                found.getFirstName(),
                found.getLastName(),
                found.getJobTitle());
        for (Project project : projects) {
            System.out.printf("    %s\n",project.getName());
        }



        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
