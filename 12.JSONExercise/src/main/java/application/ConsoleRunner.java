package application;

import application.dto.CategoryDto;
import application.dto.ProductDto;
import application.dto.UserDto;
import application.entities.Category;
import application.entities.Product;
import application.entities.User;
import application.repositories.CategoryRepository;
import application.repositories.ProductRepository;
import application.repositories.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.misc.IOUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class ConsoleRunner implements CommandLineRunner {

    CategoryRepository categoryRepository;
    ProductRepository productRepository;
    UserRepository userRepository;


    @Autowired
    public ConsoleRunner(CategoryRepository categoryRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... strings) throws Exception {

        List<Product> result = this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(new BigDecimal(500), new BigDecimal(1000));
        for (Product product : result) {
            System.out.println(product.getName());
        }
    }
}
