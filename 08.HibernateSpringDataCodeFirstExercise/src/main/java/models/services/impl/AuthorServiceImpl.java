package models.services.impl;

import models.entities.Author;
import models.repositories.AuthorRepository;
import models.services.api.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findAutor(long id) {
       return authorRepository.findOne(id);
    }

    @Override
    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }
}
