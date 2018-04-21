package models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column()
    private BigDecimal balance;

    @ManyToOne(targetEntity = User.class,optional = false)
    private User user;

    public Account() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        if(BigDecimal.ZERO.compareTo(balance)>0){
            throw new IllegalArgumentException("Balance cannot be negative!");
        }
        this.balance = balance;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
