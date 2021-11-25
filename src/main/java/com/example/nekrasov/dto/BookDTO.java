package com.example.nekrasov.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookDTO {
    private Long id;
    private String name;
    private String genre;
    private List<String> authors;
    private List<String> comments;
}
