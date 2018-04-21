package bill_system_db.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bank_accounts")
public class BankAccount extends BasicBillingDetail {

    private String bankName;
    private String swiftCode;

    @Column(name = "bank_name")
    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Column(name = "swift_code")
    public String getSwiftCode() {
        return this.swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}
