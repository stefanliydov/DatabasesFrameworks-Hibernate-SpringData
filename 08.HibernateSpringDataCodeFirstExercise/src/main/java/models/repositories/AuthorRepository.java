package models.repositories;

import models.entities.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author,Long> {

    List<Author> findAllByFirstNameEndingWith(String end);
    List<Author> findAllByLastNameStartingWith(String start);
}
