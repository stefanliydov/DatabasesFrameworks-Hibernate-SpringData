package org.softuni.mostwanted.entities.models;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "races")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ColumnDefault("0")
    @Column(nullable = false)
    private int laps;
    @NotNull
    @ManyToOne(targetEntity = District.class)
    private District district;
    @OneToMany(mappedBy = "race")
    private Set<RaceEntry> entries;

    public Race() {
        this.entries = new HashSet<>();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLaps() {
        return this.laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public District getDistrict() {
        return this.district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Set<RaceEntry> getRaceEntrys() {
        return this.entries;
    }

    public void setRaceEntrys(Set<RaceEntry> raceEntrys) {
        this.entries = raceEntrys;
    }
}
