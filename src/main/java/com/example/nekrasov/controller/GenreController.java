package com.example.nekrasov.controller;

import com.example.nekrasov.dto.GenreDTO;
import com.example.nekrasov.entity.Genre;
import com.example.nekrasov.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    List<GenreDTO> fetchGenres() {
        return genreService.listGenre();
    }

    @GetMapping("/{id}")
    GenreDTO fetchGenre(@PathVariable Long id) {
        return genreService.getGenre(id);
    }

    @PostMapping
    GenreDTO addGenre(@RequestParam String genre) {
        return genreService.addGenre(genre);
    }

    @PutMapping("/{id}")
    GenreDTO replaceGenre(@RequestParam String genre, @PathVariable Long id) {
        return genreService.replaceGenre(genre, id);
    }

    @DeleteMapping("/{id}")
    void deleteGenre(@PathVariable Long id) {
        genreService.remove(id);
    }
}
