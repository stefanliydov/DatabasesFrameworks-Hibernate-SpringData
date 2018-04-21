package entities.ingredient;

import entities.shampoo.Shampoo;

import java.math.BigDecimal;
import java.util.Set;

public abstract class BasicIngredient implements Ingredient {

    private int id;

    private String name;

    private BigDecimal price;

    private Set<Shampoo> shampoos;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Shampoo> getShampoos() {
        return this.shampoos;
    }

    public void setShampoos(Set<Shampoo> shampoos) {
        this.shampoos = shampoos;
    }
}
