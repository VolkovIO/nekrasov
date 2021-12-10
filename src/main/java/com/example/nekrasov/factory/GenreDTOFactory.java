package com.example.nekrasov.factory;

import com.example.nekrasov.dto.GenreDTO;
import com.example.nekrasov.entity.Book;
import com.example.nekrasov.entity.Genre;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class GenreDTOFactory {

    public GenreDTO createGenreDTO(Genre entity) {
        int sizeOfWrittenBooks = entity.getBooks().size();
        List<String> listNameBooks = sizeOfWrittenBooks == 0
                ? new ArrayList<>(1)
                : entity.getBooks().stream()
                .map(Book::getName)
                .collect(Collectors.toList());

        return GenreDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .books(listNameBooks)
                .build();
    }

    public List<GenreDTO> createGenreDTOList(List<Genre> entities) {
        return entities.stream()
                .map(this::createGenreDTO)
                .collect(Collectors.toList());
    }
}
