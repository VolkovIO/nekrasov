package com.example.nekrasov.service;

import com.example.nekrasov.dto.GenreDTO;
import com.example.nekrasov.entity.Genre;
import com.example.nekrasov.excepsion.BadRequestException;
import com.example.nekrasov.excepsion.NotFoundException;
import com.example.nekrasov.factory.GenreDTOFactory;
import com.example.nekrasov.repository.GenreRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreDTOFactory genreDTOFactory;

    @Override
    public List<GenreDTO> listGenre() {
        List<Genre> genres = genreRepository.findAll();
        return genreDTOFactory.createGenreDTOList(genres);
    }

    @Override
    public GenreDTO getGenre(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Жанр с идентификатором \"%s\" не найден.", id)));
        return genreDTOFactory.createGenreDTO(genre);
    }

    @Override
    public GenreDTO addGenre(String genre) {
        if (genreRepository.existsByName(genre)) {
            throw new BadRequestException("Жанр " + genre + " уже существует.");
        }

        Genre saveGenre = genreRepository.save(Genre.makeDefault(genre));
        return genreDTOFactory.createGenreDTO(saveGenre);
    }

    @Override
    public GenreDTO replaceGenre(String name, Long id) {

        Genre genreForDTO = genreRepository.findById(id)
                .map(genre -> {
                    genre.setName(name);
                    return genreRepository.save(genre);
                })
                .orElseGet(() -> {
                    if (genreRepository.existsByName(name)) {
                        throw new BadRequestException("Жанр " + name + " уже существует, но не с id " + id);
                    }
                    return genreRepository.save(Genre.makeDefault(name));
                });
        return genreDTOFactory.createGenreDTO(genreForDTO);
    }

    @Override
    public void remove(Long id) {
        genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Жанр с идентификатором \"%s\" не найден.", id)));
        genreRepository.deleteById(id);
    }
}
