package app;

import app.model.enums.Size;
import app.model.ingredients.BasicIngredient;
import app.model.ingredients.Ingredient;
import app.model.shampoos.BasicShampoo;
import app.repositories.IngredientRepository;
import app.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ConsoleRunner implements CommandLineRunner {


    private ShampooRepository shampooRepository;
    private IngredientRepository ingridientRepository;

    @Autowired
    public ConsoleRunner(ShampooRepository shampooRepository, IngredientRepository ingridientRepository) {
        this.shampooRepository = shampooRepository;
        this.ingridientRepository = ingridientRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        // findBySize();
        // findBySizeOrLabelIdOrderByPrice();
        // SelectByPrice();
        // SelectIngredientsByFirstLetter();
        // SelectIngredientsInList();

       // System.out.println(shampooRepository.findByPriceLessThan(new BigDecimal(8.50)).size());

    }

    private void SelectIngredientsInList() {
        List<String> names = new ArrayList<>();
        names.add("Lavender");
        names.add("Herbs");
        names.add("Apple");
        List<BasicIngredient> ingredients = ingridientRepository.findByNameInOrderByPriceAsc(names);
        for (BasicIngredient ingredient : ingredients) {
            System.out.println(ingredient.getName());
        }
    }

    private void SelectIngredientsByFirstLetter() {
        List<BasicIngredient> ingredients = ingridientRepository.findByNameStartingWith("M");
        for (BasicIngredient ingredient : ingredients) {
            System.out.println(ingredient.getName());
        }
    }

    private void SelectByPrice() {
        List<BasicShampoo> byPriceGreaterThanOOrderByPriceDesc = shampooRepository
                .findByPriceGreaterThanOrderByPriceDesc(new BigDecimal(5));
        for (BasicShampoo basicShampoo : byPriceGreaterThanOOrderByPriceDesc) {
            System.out.println(basicShampoo.getBrand());
        }
    }

    private void findBySizeOrLabelIdOrderByPrice() {
        List<BasicShampoo> bySizeOrIdIsLikeOOrderByPriceAsc = this.shampooRepository.findBySizeOrLabelIdIsLikeOrderByPriceAsc(Size.MEDIUM, 10L);
        for (BasicShampoo basicShampoo : bySizeOrIdIsLikeOOrderByPriceAsc) {
            System.out.println(basicShampoo.getBrand());
        }
    }

    private void findBySize() {
        List<BasicShampoo> bySize = this.shampooRepository.findBySizeOrderByBrand(Size.MEDIUM);
        for (BasicShampoo shampoo : bySize) {
            System.out.println(shampoo.getBrand());
        }
    }

}
