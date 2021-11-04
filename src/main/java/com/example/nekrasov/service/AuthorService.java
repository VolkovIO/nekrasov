package com.example.nekrasov.service;

import com.example.nekrasov.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author> listAuthor();
    Author getAuthor(Long id);
    Author addAuthor(Author author);
    Author replaceAuthor(Author author, Long id);
    void remove(Long id);
}
