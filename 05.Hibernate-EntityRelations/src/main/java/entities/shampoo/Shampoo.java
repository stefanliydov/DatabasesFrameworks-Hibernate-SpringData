package entities.shampoo;

import entities.batch.ProductionBatch;
import entities.ingredient.Ingredient;
import entities.label.BasicLabel;

import java.math.BigDecimal;
import java.util.Set;

 interface Shampoo {

     long getId();

     void setId(long id);

     String getBrand();

     void setBrand(String brand);

     BasicLabel getLabel();

     void setLabel(BasicLabel label);

     ProductionBatch getBatch();

     void setBatch(ProductionBatch batch);

     BigDecimal getPrice();

     void setPrice(BigDecimal price);

     Set<Ingredient> getIngredients();

     void setIngredients(Set<Ingredient> ingredients);
}
