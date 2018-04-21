package app.repositories;

import app.model.ingredients.BasicIngredient;
import app.model.ingredients.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<BasicIngredient, Long> {

    List<BasicIngredient> findByNameStartingWith(String letter);
    List<BasicIngredient> findByNameInOrderByPriceAsc(List<String> ingredients);

    @Override
    BasicIngredient findOne(Long id);

    @Modifying
    @Transactional
    void deleteByName(String name);
}
