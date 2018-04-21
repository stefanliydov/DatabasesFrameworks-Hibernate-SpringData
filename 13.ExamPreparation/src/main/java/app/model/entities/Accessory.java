package app.model.entities;

import javax.persistence.*;

/**
 * Created by User on 23.7.2017 г..
 */

@Entity
@Table(name = "accessories")
public class Accessory {

    private long id;
    private String name;
    private Photographer owner;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(targetEntity = Photographer.class)
    public Photographer getOwner() {
        return this.owner;
    }

    public void setOwner(Photographer owner) {
        this.owner = owner;
    }
}
