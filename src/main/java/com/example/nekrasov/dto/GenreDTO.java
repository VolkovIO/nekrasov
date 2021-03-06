package com.example.nekrasov.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GenreDTO {
    private Long id;
    private String name;
    private List<String> books;
}
