package app.retake.domain.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name",nullable = false)
    @Size(min = 3, max =20)
    private String name;

    @Column(name = "type",nullable = false)
    @Size(min = 3, max =20)
    private String type;

    @Column(name = "age")
    @Min(1)
    private Integer age;

    @OneToOne(targetEntity = Passport.class)
    private Passport passport;

    @OneToMany(mappedBy = "animal",cascade = CascadeType.PERSIST)
    private Set<Procedure> procedures;

    public Animal() {
        this.procedures = new HashSet<>();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Passport getPassport() {
        return this.passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Set<Procedure> getProcedures() {
        return this.procedures;
    }

    public void setProcedures(Set<Procedure> procedures) {
        this.procedures = procedures;
    }

    @PrePersist
    private void setRelation(){
        this.passport.setAnimal(this);
    }
}
