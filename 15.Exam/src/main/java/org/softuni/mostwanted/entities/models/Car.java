package org.softuni.mostwanted.entities.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "brand",nullable = false)
    private String brand;

    @Column(name = "model",nullable = false)
    private String model;

    private BigDecimal price;

    @Column(name = "year_of_prodction",nullable = false)
    private Integer yearOfProduction;

    private BigDecimal maxSpeed;

    private BigDecimal zeroToSixty;
    @ManyToOne(targetEntity = Racer.class,fetch = FetchType.EAGER)
    private Racer racer;
    @OneToMany(mappedBy = "car")
    private Set<RaceEntry> raceEntries;

    public Car() {
        this.raceEntries = new HashSet<>();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getYearOfProduction() {
        return this.yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public BigDecimal getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(BigDecimal maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public BigDecimal getZeroToSixty() {
        return this.zeroToSixty;
    }

    public void setZeroToSixty(BigDecimal zeroToSixty) {
        this.zeroToSixty = zeroToSixty;
    }

    public Racer getRacer() {
        return this.racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

    public Set<RaceEntry> getRaceEntries() {
        return this.raceEntries;
    }

    public void setRaceEntries(Set<RaceEntry> raceEntries) {
        this.raceEntries = raceEntries;
    }
}
