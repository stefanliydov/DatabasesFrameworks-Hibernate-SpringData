package app.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "workshops")
public class Workshop {
    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private String location;
    private BigDecimal pricePerParticipant;
    private Photographer trainer;
    private Set<Photographer> participants;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "start_date")
    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date")
    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "location")
    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column(name = "price_per_participants")
    public BigDecimal getPricePerParticipant() {
        return this.pricePerParticipant;
    }

    public void setPricePerParticipant(BigDecimal pricePerParticipant) {
        this.pricePerParticipant = pricePerParticipant;
    }

    @ManyToOne(targetEntity = Photographer.class)
    public Photographer getTrainer() {
        return this.trainer;
    }

    public void setTrainer(Photographer trainer) {
        this.trainer = trainer;
    }

    @ManyToMany
    @JoinTable(name = "workshop_participants",
    joinColumns = @JoinColumn(name = "workshop_id"),
    inverseJoinColumns = @JoinColumn(name = "participant_id"))
    public Set<Photographer> getParticipants() {
        return this.participants;
    }

    public void setParticipants(Set<Photographer> participants) {
        this.participants = participants;
    }

}
