package app.repositories;

import app.model.enums.Size;
import app.model.ingredients.Ingredient;
import app.model.labels.Label;
import app.model.shampoos.BasicShampoo;
import app.model.shampoos.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<BasicShampoo,Long> {
    List<BasicShampoo> findBySizeOrderByBrand (Size size);
    List<BasicShampoo> findBySizeOrLabelIdIsLikeOrderByPriceAsc(Size size, Long id);
    List<BasicShampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);
    List<BasicShampoo> findByPriceLessThan(BigDecimal price);

    @Query("Select i.shampoos " +
            "FROM BasicIngredient as i " +
            "Where i IN:ingredients")
    List<BasicShampoo> findByHavingIngredients(@Param("ingredients")List<? extends Ingredient> ingredients);


    @Query("SELECT s " +
            "FROM BasicShampoo as s " +
            "WHERE s.ingredients.size < :num ")
    List<BasicShampoo> findByIngredientsCountLessThen(@Param("num") int num);






}
