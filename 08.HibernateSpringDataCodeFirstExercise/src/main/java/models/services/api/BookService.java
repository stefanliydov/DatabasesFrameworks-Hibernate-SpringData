package models.services.api;

import models.entities.Book;

import java.text.ParseException;

public interface BookService  {

    void saveBook(Book book);

    void getAllBooksByAgeRestriction(String input);

    void getBooksByAuthorWithLastNameStartingWith(String start);

    void allBooksWithTitleLengthLongerThan(int length);

    void getAllBooksByEditionTypeAndCopiesLessThan();

    void increaseCopiesAfterDate(String input, int copies) throws ParseException;

    void deleteBooks(int size);
}
