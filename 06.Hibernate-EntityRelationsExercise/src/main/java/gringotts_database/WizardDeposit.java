package gringotts_database;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="wizard_deposits ")
public class WizardDeposit {

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="first_name",length = 50)
    private String firstName;

    @Column(name = "last_name",nullable = false,length = 60)
    private String  lastName;

    @Column(name = "notes",length = 1000)
    private String notes;

    @Column(name = "age",nullable = false)
    private int age;

    @Column(name = "magic_wand_creator",length = 100)
    private String magicWandCreator;

    @Column(name = "magic_wand_size", columnDefinition = "INT(7)")
    private int magicWandSize;

    @Column(name = "deposit_group",length = 20)
    private String depositGroup;

    @Column(name="deposit_start_date")
    private Date depositStartDate;

    @Column(name = "deposit_amount")
    private double depositAmount;

    @Column(name ="deposit_interest")
    private double depositInterest;

    @Column(name = "deposit_charge")
    private double depositCharge;

    @Column(name ="deposit_expiration_date")
    private Date depositExpirationDate;

    @Column(name = "is_deposit_expired")
    private boolean isDepositExpired;

    public WizardDeposit(){
        this.age =0;
        this.lastName ="";
    }

}
