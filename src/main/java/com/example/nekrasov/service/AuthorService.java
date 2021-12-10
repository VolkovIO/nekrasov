package com.example.nekrasov.service;

import com.example.nekrasov.dto.AuthorDTO;
import com.example.nekrasov.entity.Author;

import java.util.List;

public interface AuthorService {
    List<AuthorDTO> listAuthor();

    AuthorDTO getAuthor(Long id);

    AuthorDTO addAuthor(String author);

    AuthorDTO replaceAuthor(String name, Long id);

    void remove(Long id);
}
