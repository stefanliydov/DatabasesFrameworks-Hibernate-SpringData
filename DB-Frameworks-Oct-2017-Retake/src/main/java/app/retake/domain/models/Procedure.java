package app.retake.domain.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "procedures")
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(targetEntity = Animal.class)
    private Animal animal;
    @Transient
    private BigDecimal cost;
    @ManyToOne
    private Vet vet;
    private Date datePerformed;
    @ManyToMany
    private Set<AnimalAid> services;

    public Procedure() {
        this.services = new HashSet<>();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return this.animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public BigDecimal getCost() {
        BigDecimal cost =  BigDecimal.ZERO;
        for (AnimalAid service : this.services) {
                cost = cost.add(service.getPrice());
        }
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Vet getVet() {
        return this.vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public Date getDatePerformed() {
        return this.datePerformed;
    }

    public void setDatePerformed(Date datePerformed) {
        this.datePerformed = datePerformed;
    }

    public Set<AnimalAid> getServices() {
        return this.services;
    }

    public void setServices(Set<AnimalAid> services) {
        this.services = services;
    }
}
