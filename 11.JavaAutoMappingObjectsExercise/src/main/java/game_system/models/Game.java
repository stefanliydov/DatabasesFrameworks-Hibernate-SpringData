package game_system.models;

import sun.plugin.viewer.context.IExplorerAppletContext;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;
import java.util.regex.Pattern;

@Entity
@Table(name = "games")
public class Game {

    private long id;
    private String title;
    private double price;
    private double size;
    private String trailer;
    private String thumbnailUrl;
    private String description;
    private Set<Order> orders;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return this.id;
    }


    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if (title.length() < 3 || title.length() > 100) {
            throw new IllegalArgumentException("Title either too short or too long.");
        }
        Pattern pattern = Pattern.compile("^[A-Z].+");
        if (!pattern.matcher(title).matches()) {
            throw new IllegalArgumentException("Title doesn't start with a capital letter");
        }

        this.title = title;
    }

    @Column(name = "price")
    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    @Column(name = "size")
    public double getSize() {
        return this.size;
    }

    public void setSize(double size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size cannot be negative");
        }
        this.size = size;
    }

    @Column(name = "trailer")
    public String getTrailer() {
        return this.trailer;
    }

    public void setTrailer(String trailer) {
        if (trailer.length() != 11) {
            throw new IllegalArgumentException("Invalid trailer");
        }
        this.trailer = trailer;
    }

    @Column(name = "thumbnail_url")
    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        Pattern pattern = Pattern.compile("(http|https).+");
        if (!pattern.matcher(thumbnailUrl).matches()) {
            throw new IllegalArgumentException("Invalid Thumbnail URL");
        }
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        if (description.length() < 20) {
            throw new IllegalArgumentException("Description must be at least 20 symbols long");
        }
        this.description = description;
    }

    @ManyToMany(mappedBy = "products")
    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }




}
