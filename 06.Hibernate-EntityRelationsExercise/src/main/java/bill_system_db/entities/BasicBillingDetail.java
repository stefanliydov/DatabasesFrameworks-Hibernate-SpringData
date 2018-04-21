package bill_system_db.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public abstract class BasicBillingDetail {
    private String number;
    private User owner;

    public BasicBillingDetail() {
    }

    @Id
    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    @ManyToOne(targetEntity = User.class)
    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
