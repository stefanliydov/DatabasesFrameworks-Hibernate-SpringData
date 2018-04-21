package game_system.models;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import javax.persistence.*;
import java.util.Set;
import java.util.regex.Pattern;

@Entity
@Table(name = "users")
public class User {

    private long id;
    private String email;
    private String password;
    private String fullName;
    private Set<Order> orders;
    private Set<Game> games;
    private boolean isAdmin;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "email",unique = true)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+@[a-z0-9]+\\.[a-z.0-9]+$");
        if (pattern.matcher(email).matches()) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    @Transient
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {

        if(password.length()<6){
            throw new  IllegalArgumentException("Password must be at least 6 symbols long");
        }
        this.password = password;
    }

    @Column(name = "full_name")
    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @OneToMany(mappedBy = "buyer")
    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @OneToMany(targetEntity = Game.class)
    public Set<Game> getGames() {
        return this.games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @Column(name = "is_admin")
    public boolean isAdmin() {
        return this.isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
