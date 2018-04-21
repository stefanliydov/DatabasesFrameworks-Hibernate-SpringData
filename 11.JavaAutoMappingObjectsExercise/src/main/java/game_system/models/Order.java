package game_system.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name ="orders")
public class Order {

    private long id;
    private User buyer;
    private Set<Game> products;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(targetEntity = User.class)
    public User getBuyer() {
        return this.buyer;
    }


    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @ManyToMany
    @JoinTable(name = "orders_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    public Set<Game> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Game> products) {
        this.products = products;
    }
}
