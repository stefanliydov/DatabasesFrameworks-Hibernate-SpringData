package app.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "addresses")
public class Address {

    private long id;

    private String city;

    private String country;

    private Set<Employee> employees;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @OneToMany(targetEntity = Employee.class,mappedBy = "address")
    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
