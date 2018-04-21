package sales_database.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "locations")
public class Location {
    private long id;
    private String locationName;
    private Set<Sale> sales;

    public Location() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getLocationName() {
        return this.locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @OneToMany(mappedBy = "location")
    public Set<Sale> getSales() {
        return this.sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
