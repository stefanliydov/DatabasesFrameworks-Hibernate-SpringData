package org.softuni.mostwanted.entities.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "districts")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name",nullable = false)
    private String name;
    @ManyToOne(targetEntity = Town.class)
    private Town town;
    @OneToMany(mappedBy = "district")
    private Set<Race> races;

    public District() {
        this.races =  new HashSet<>();
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

    public Town getTown() {
        return this.town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Set<Race> getRaces() {
        return this.races;
    }

    public void setRaces(Set<Race> races) {
        this.races = races;
    }
}
