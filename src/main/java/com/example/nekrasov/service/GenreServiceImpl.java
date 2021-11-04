package com.example.nekrasov.service;

import com.example.nekrasov.entity.Genre;
import com.example.nekrasov.excepsion.NotFoundException;
import com.example.nekrasov.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> listGenre() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenre(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Жанр с идентификатором \"%s\" не найден.", id)));
    }

    @Override
    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre replaceGenre(Genre newGenre, Long id) {

        return genreRepository.findById(id)
                .map(genre -> {
                    genre.setId(newGenre.getId());
                    genre.setName(newGenre.getName());
                    return genreRepository.save(genre);
                })
                .orElseGet(() -> {
                    newGenre.setId(id);
                    return genreRepository.save(newGenre);
                });
    }

    @Override
    public void remove(Long id) {
        genreRepository.deleteById(id);
    }
}
