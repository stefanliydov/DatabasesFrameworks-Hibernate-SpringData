package org.softuni.mostwanted.entities.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name",nullable = false)
    private String name;
    @OneToMany(mappedBy = "town")
    private Set<District> districts;
    @OneToMany(mappedBy = "homeTown")
    private Set<Racer> racers;
    public Integer getId() {
        return this.id;
    }

    public Town() {
        this.districts = new HashSet<>();
        this.racers = new HashSet<>();
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

    public Set<District> getDistricts() {
        return this.districts;
    }

    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }

    public Set<Racer> getRacers() {
        return this.racers;
    }

    public void setRacers(Set<Racer> racers) {
        this.racers = racers;
    }

}
