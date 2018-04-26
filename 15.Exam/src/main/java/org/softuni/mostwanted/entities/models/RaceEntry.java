package org.softuni.mostwanted.entities.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "race_entries")
public class RaceEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private boolean hasFinished;
    private BigDecimal finishTime;
    @ManyToOne(targetEntity = Car.class)
    private Car car;
    @ManyToOne(targetEntity = Racer.class)
    private Racer racer;
    @ManyToOne(targetEntity = Race.class)
    private Race race;

    public RaceEntry() {

    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isHasFinished() {
        return this.hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public BigDecimal getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(BigDecimal finishTime) {
        this.finishTime = finishTime;
    }

    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Racer getRacer() {
        return this.racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

    public Race getRace() {
        return this.race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
