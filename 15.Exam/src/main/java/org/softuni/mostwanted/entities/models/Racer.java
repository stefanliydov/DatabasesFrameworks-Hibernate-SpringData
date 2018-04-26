package org.softuni.mostwanted.entities.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "racers")
public class Racer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name",nullable = false)
    private String name;
    private Integer age;
    private BigDecimal bounty;
    @ManyToOne(targetEntity = Town.class)
    private Town homeTown;
    @OneToMany(mappedBy = "racer",fetch = FetchType.EAGER)
    private Set<Car> cars;
    @OneToMany(mappedBy = "racer")
    private Set<RaceEntry> raceEntries;
    public Integer getId() {
        return this.id;
    }

    public Racer() {
        this.cars = new HashSet<>();
        this.raceEntries = new HashSet<>();
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

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getBounty() {
        return this.bounty;
    }

    public void setBounty(BigDecimal bounty) {
        this.bounty = bounty;
    }

    public Town getHomeTown() {
        return this.homeTown;
    }

    public void setHomeTown(Town homeTown) {
        this.homeTown = homeTown;
    }

    public Set<Car> getCars() {
        return this.cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Set<RaceEntry> getRaceEntries() {
        return this.raceEntries;
    }

    public void setRaceEntries(Set<RaceEntry> raceEntries) {
        this.raceEntries = raceEntries;
    }

}
