package app.retake.domain.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "passports")
public class Passport{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String serialNumber;
    @OneToOne(mappedBy = "passport")
    private Animal animal;
    @Column(name = "owner_phone_number",nullable = false)
    private String ownerPhoneNumber;
    @Column(name = "owner_name")
    private String ownerName;
    @Column(name = "registered_on")
    private Date registrationDate;

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Animal getAnimal() {
        return this.animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getOwnerPhoneNumber() {
        return this.ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

}
