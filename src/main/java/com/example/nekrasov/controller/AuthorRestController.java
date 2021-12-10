package com.example.nekrasov.controller;

import com.example.nekrasov.dto.AuthorDTO;
import com.example.nekrasov.entity.Author;
import com.example.nekrasov.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {

    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    List<AuthorDTO> fetchAuthors() {
        return authorService.listAuthor();
    }

    @GetMapping("/{id}")
    AuthorDTO fetchAuthor(@PathVariable Long id) {
        return authorService.getAuthor(id);
    }

    @PostMapping
    AuthorDTO addAuthor(@RequestParam String author) {
        return authorService.addAuthor(author);
    }

    @PutMapping("/{id}")
    AuthorDTO addAuthor(@RequestParam String author, @PathVariable Long id) {
        return authorService.replaceAuthor(author, id);
    }

    @DeleteMapping("/{id}")
    void deleteAuthor(@PathVariable Long id) {
        authorService.remove(id);
    }

}
