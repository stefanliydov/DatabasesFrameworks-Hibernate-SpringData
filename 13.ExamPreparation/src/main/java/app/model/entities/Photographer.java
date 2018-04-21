package app.model.entities;

import org.hibernate.jdbc.Work;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name = "photographers")
public class Photographer {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private BasicCamera primaryCamera;
    private BasicCamera secondaryCamera;
    private Set<Accessory> accessories;
    private Set<Workshop> trainerIn;
    private Set<Workshop> participantIn;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "phone")
    @Pattern(regexp = "^\\+[0-9]{1,3}/[0-9]{8,10}$")
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @ManyToOne(targetEntity = BasicCamera.class, optional = false)
    public BasicCamera getPrimaryCamera() {
        return this.primaryCamera;
    }

    public void setPrimaryCamera(BasicCamera primaryCamera) {
        this.primaryCamera = primaryCamera;
    }
    @ManyToOne(targetEntity = BasicCamera.class)
    public BasicCamera getSecondaryCamera() {
        return this.secondaryCamera;
    }

    public void setSecondaryCamera(BasicCamera secondaryCamera) {
        this.secondaryCamera = secondaryCamera;
    }

    @OneToMany(mappedBy = "owner")
    public Set<Accessory> getAccessories() {
        return this.accessories;
    }

    public void setAccessories(Set<Accessory> accessories) {
        this.accessories = accessories;
    }

    @OneToMany(mappedBy = "trainer")
    public Set<Workshop> getTrainerIn() {
        return this.trainerIn;
    }

    public void setTrainerIn(Set<Workshop> trainerIn) {
        this.trainerIn = trainerIn;
    }

    @ManyToMany(mappedBy = "participants")
    public Set<Workshop> getParticipantIn() {
        return this.participantIn;
    }

    public void setParticipantIn(Set<Workshop> participantIn) {
        this.participantIn = participantIn;
    }
}
