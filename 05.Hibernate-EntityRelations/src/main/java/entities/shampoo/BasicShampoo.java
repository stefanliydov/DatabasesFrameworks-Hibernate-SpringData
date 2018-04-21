package entities.shampoo;

import entities.batch.ProductionBatch;
import entities.ingredient.Ingredient;
import entities.label.BasicLabel;

import java.math.BigDecimal;
import java.util.Set;

public abstract class BasicShampoo implements Shampoo {

    private long id;

    private String brand;

    private BasicLabel label;

    private ProductionBatch batch;

    private BigDecimal price;

 //   private Size size;

    private Set<Ingredient> ingredients;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BasicLabel getLabel() {
        return this.label;
    }

    public void setLabel(BasicLabel label) {
        this.label = label;
    }

    public ProductionBatch getBatch() {
        return this.batch;
    }

    public void setBatch(ProductionBatch batch) {
        this.batch = batch;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
