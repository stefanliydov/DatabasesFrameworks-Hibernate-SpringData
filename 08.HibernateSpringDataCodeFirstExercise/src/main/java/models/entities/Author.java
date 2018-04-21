package models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private long authorId;

    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToMany
    private Set<Book> booksByAuthor;

    public long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooksByAuthor() {
        return this.booksByAuthor;
    }

    public void setBooksByAuthor(Set<Book> booksByAuthor) {
        this.booksByAuthor = booksByAuthor;
    }
}
