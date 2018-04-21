package models.services.impl;

import models.entities.Author;
import models.entities.Book;
import models.enums.AgeRestriction;
import models.repositories.AuthorRepository;
import models.repositories.BookRepository;
import models.services.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void getAllBooksByAgeRestriction(String input) {
        input = input.toUpperCase();
        AgeRestriction ageRestriction = AgeRestriction.valueOf(input);

        List<Book> found = bookRepository.findAllByAgeRestriction(ageRestriction.getValue());
        for (Book book : found) {
            System.out.println(book.getTitle());
        }

    }

    @Override
    public void getBooksByAuthorWithLastNameStartingWith(String start) {
        List<Author> authors = authorRepository.findAllByLastNameStartingWith(start);
        for (Author author : authors) {
            Set<Book> booksByAuthor = author.getBooksByAuthor();
            for (Book book : booksByAuthor) {
                System.out.printf("%s (%s %s)\n", book.getTitle(), author.getFirstName(), author.getLastName());
            }
        }
    }

    @Override
    public void allBooksWithTitleLengthLongerThan(int length) {
        System.out.println(bookRepository.countAllByTitleGreaterThan(length));
    }

    @Override
    public void getAllBooksByEditionTypeAndCopiesLessThan() {
    }

    @Override
    public void increaseCopiesAfterDate(String input, int copies) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd M yyyy");
        Date date = dateFormat.parse(input);
        List<Book> books = bookRepository.findAllByReleaseDateAfter(date);
        int total = 0;
        for (Book book : books) {
            //       bookRepository.increaseCopies(book.getBookId(),copies);
            total += copies;
        }
        System.out.println(total);
    }

    @Override
    public void deleteBooks(int size) {
        List<Book> booksFound = bookRepository.findAllByCopiesLessThan(size);
        try {
        this.bookRepository.deleteWithCopiesLessThan(size);
        System.out.println(booksFound.size() + " Books were deleted.");

        }catch (Error ignored){}

    }

}
