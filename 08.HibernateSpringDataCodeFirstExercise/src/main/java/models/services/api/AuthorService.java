package models.services.api;


import models.entities.Author;

public interface AuthorService {

    Author findAutor(long id);
    void saveAuthor(Author author);
}
