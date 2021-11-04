package com.example.nekrasov.service;

import com.example.nekrasov.entity.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenreService {
    List<Genre> listGenre();
    Genre getGenre(Long id);
    Genre addGenre(Genre genre);
    Genre replaceGenre(Genre genre, Long id);
    void remove(Long id);
}
