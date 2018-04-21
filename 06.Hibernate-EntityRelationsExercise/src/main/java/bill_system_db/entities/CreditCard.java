package bill_system_db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "credit_cards")
public class CreditCard extends BasicBillingDetail{

    private String cardType;
    private Date expirationMonth;
    private Date expirationYear;


    @Column(name = "card_type")
    public String getCardType() {
        return this.cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    @Column(name = "expiration_month")
    public Date getExpirationMonth() {
        return this.expirationMonth;
    }

    public void setExpirationMonth(Date expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    @Column(name = "expiration_year")
    public Date getExpirationYear() {
        return this.expirationYear;
    }

    public void setExpirationYear(Date expirationYear) {
        this.expirationYear = expirationYear;
    }
}
