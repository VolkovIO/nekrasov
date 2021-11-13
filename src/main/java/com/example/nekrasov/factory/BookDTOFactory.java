package com.example.nekrasov.factory;

import com.example.nekrasov.dto.BookDTO;
import com.example.nekrasov.entity.Book;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class BookDTOFactory {

    public BookDTO createBookDTO(Book entity){
        return BookDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public List<BookDTO> createBookDTOList(List<Book> entities){
        return entities.stream()
                .map(this::createBookDTO)
                .collect(Collectors.toList());
    }
}
