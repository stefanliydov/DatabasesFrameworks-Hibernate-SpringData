package app.entities;



import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {

    private long id;

    private String firstName;

    private String lastName;

    private BigDecimal saraly;

    private LocalDate birthDate;

    private Address address;

    private boolean onHoliday;

    private Employee manager;

    private Set<Employee> employees;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSaraly() {
        return this.saraly;
    }

    public void setSaraly(BigDecimal saraly) {
        this.saraly = saraly;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @ManyToOne(cascade = CascadeType.MERGE )
    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isOnHoliday() {
        return this.onHoliday;
    }

    public void setOnHoliday(boolean onHoliday) {
        this.onHoliday = onHoliday;
    }

    @ManyToOne
    public Employee getManager() {
        return this.manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @OneToMany(mappedBy = "manager",fetch = FetchType.EAGER)
    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }


}
