package entities.ingredient;

import entities.shampoo.Shampoo;

import java.math.BigDecimal;
import java.util.Set;

public interface Ingredient {

     int getId();

     void setId(int id);

     String getName();

     void setName(String name);

     BigDecimal getPrice();

     void setPrice(BigDecimal price);

     Set<Shampoo> getShampoos();

     void setShampoos(Set<Shampoo> shampoos);
}
