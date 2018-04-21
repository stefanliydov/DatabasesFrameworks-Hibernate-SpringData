package application.entities;

import javax.persistence.*;
import java.util.Set;

@Entity

public class User  {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Set<User> friends;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(nullable = false)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @ManyToMany
    @JoinTable(name = "user_friends",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "friend_id"))
    public Set<User> getFriends() {
        return this.friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }
}
