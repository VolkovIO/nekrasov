package com.example.nekrasov.factory;

import com.example.nekrasov.dto.BookDTO;
import com.example.nekrasov.entity.Author;
import com.example.nekrasov.entity.Book;
import com.example.nekrasov.entity.Comment;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class BookDTOFactory {

    public BookDTO createBookDTO(Book entity) {

        List<String> listAuthors = entity.getAuthors().stream()
                .map(Author::getName)
                .collect(Collectors.toList());

        List<String> listComments = entity.getComments().stream()
                .map(Comment::getComment)
                .collect(Collectors.toList());

        return BookDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .genre(entity.getGenre().getName())
                .authors(listAuthors)
                .comments(listComments)
                .build();
    }

    public List<BookDTO> createBookDTOList(List<Book> entities) {
        return entities.stream()
                .map(this::createBookDTO)
                .collect(Collectors.toList());
    }
}
