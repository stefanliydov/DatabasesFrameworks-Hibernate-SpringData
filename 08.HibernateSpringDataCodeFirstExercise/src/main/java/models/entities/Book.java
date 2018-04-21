package models.entities;


import models.entities.Author;
import models.enums.AgeRestriction;
import models.enums.EditionType;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long bookId;

    @Column(nullable = false)
    private int ageRestriction;
    @Column(nullable = false)
    private int copies;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private int editionType;

    @Column(nullable = false)
    private double price;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "title",nullable = false)
    private String title;

    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "author_id",foreignKey =@ForeignKey(name ="fk_author_id"))
    private Author author;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name ="book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    public long getBookId() {
        return this.bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public int getAgeRestriction() {
        return this.ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction.getValue();
    }

    public int getCopies() {
        return this.copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEditionType() {
        return this.editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType.getValue();
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
