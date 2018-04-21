package models.application;

import models.entities.Author;
import models.entities.Book;
import models.entities.ReducedBook;
import models.enums.AgeRestriction;
import models.enums.EditionType;
import models.repositories.AuthorRepository;
import models.repositories.BookRepository;
import models.repositories.CategoryRepository;
import models.services.api.AuthorService;
import models.services.api.BookService;
import models.services.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@ComponentScan({"models.services.impl"})
@EntityScan({"models.entities"})
@EnableJpaRepositories("models.repositories")
@Transactional
public class ConsoleRunner implements CommandLineRunner {

    private AuthorService authorService;
    private CategoryService categoryService;
    private BookService bookService;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private CategoryRepository categoryRepository;
    @Autowired
    public ConsoleRunner(AuthorService authorService, CategoryService categoryService, BookService bookService, BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

       //List<Author> authors = authorRepository.findAllByFirstNameEndingWith("Dy");
       //for (Author author : authors) {
       //    System.out.printf("%s %s%n",author.getFirstName(),author.getLastName());
       //}
       // bookService.getBooksByAuthorWithLastNameStartingWith("R");
      // this.bookService.increaseCopiesAfterDate("12 Oct 2005",100   );
        this.bookService.deleteBooks(4200);
    }
}
