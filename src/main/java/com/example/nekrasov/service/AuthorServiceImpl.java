package com.example.nekrasov.service;

import com.example.nekrasov.entity.Author;
import com.example.nekrasov.excepsion.NotFoundException;
import com.example.nekrasov.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAuthor() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthor(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Автор с идентификатором \"%s\" не найден.", id)));
    }

    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author replaceAuthor(Author newAuthor, Long id) {
        return authorRepository.findById(id)
                .map(author -> {
                    author.setId(newAuthor.getId());
                    author.setName(newAuthor.getName());
                    return authorRepository.save(author);
                })
                .orElseGet(() -> {
                    newAuthor.setId(id);
                    return authorRepository.save(newAuthor);
                });
    }

    @Override
    public void remove(Long id) {
        authorRepository.deleteById(id);
    }
}
