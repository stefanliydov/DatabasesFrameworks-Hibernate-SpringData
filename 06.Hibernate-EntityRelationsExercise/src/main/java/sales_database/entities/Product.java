package sales_database.entities;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


@Entity
@Table(name  = "products")
public class Product {

    private long id;
    private String name;
    private BigDecimal price;
    private Set<Sale> sales;

    public Product(){

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

    @Column(name ="name",length = 30)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @OneToMany(mappedBy = "product",targetEntity = Sale.class)
    public Set<Sale> getSales() {
        return this.sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
