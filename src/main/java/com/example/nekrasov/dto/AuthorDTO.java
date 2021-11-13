package com.example.nekrasov.dto;

import com.example.nekrasov.entity.Book;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuthorDTO {
    private Long id;
    private String name;
    private List<Book> books;

}
