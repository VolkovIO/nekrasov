package com.example.nekrasov.service;

import com.example.nekrasov.dto.GenreDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenreService {
    List<GenreDTO> listGenre();
    GenreDTO getGenre(Long id);
    GenreDTO addGenre(String genre);
    GenreDTO replaceGenre(String genre, Long id);
    void remove(Long id);
}
