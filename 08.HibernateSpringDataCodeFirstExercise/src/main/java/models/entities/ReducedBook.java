package models.entities;

import models.enums.EditionType;

public class ReducedBook {

    private String title;

    private int editionType;

    private int ageRestriction;

    private double price;

    public ReducedBook(String title, int editionType, int ageRestriction, double price) {
        this.title = title;
        this.editionType = editionType;
        this.ageRestriction = ageRestriction;
        this.price = price;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEditionType() {
        return this.editionType;
    }

    public void setEditionType(int editionType) {
        this.editionType = editionType;
    }

    public int getAgeRestriction() {
        return this.ageRestriction;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
