package com.example.nekrasov.factory;

import com.example.nekrasov.dto.AuthorDTO;
import com.example.nekrasov.entity.Author;
import com.example.nekrasov.entity.Book;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class AuthorDTOFactory {

    public AuthorDTO createAuthorDTO(Author entity) {
        int sizeOfWrittenBooks = entity.getBooks().size();
        List<String> listNameBooks = sizeOfWrittenBooks == 0
                ? new ArrayList<>(1)
                : entity.getBooks().stream()
                .map(Book::getName)
                .collect(Collectors.toList());

        return AuthorDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .books(listNameBooks)
                .build();
    }

    public List<AuthorDTO> createAuthorDTOList(List<Author> entities) {
        return entities.stream()
                .map(this::createAuthorDTO)
                .collect(Collectors.toList());
    }
}
