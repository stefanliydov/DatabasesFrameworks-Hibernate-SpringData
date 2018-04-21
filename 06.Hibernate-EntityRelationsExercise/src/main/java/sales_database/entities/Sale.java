package sales_database.entities;

import sales_database.entities.Customer;
import sales_database.entities.Location;
import sales_database.entities.Product;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales")
public class Sale {

    private long id;
    private Product product;
    private Customer customer;
    private Location location;
    private Date date;

    public Sale() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(targetEntity = Product.class)
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne(targetEntity = Customer.class)
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    @ManyToOne
    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    @Column(name="date")
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
