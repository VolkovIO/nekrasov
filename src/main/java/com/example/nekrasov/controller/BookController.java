package com.example.nekrasov.controller;

import com.example.nekrasov.dto.BookDTO;
import com.example.nekrasov.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> fetchBooks(){
        return bookService.listBook();
    }

    @GetMapping("/{id}")
    public BookDTO fetchBook(@PathVariable Long id){
        return bookService.getBook(id);
    }

    @PostMapping
    public BookDTO addBook(@RequestBody BookDTO bookDTO){
        return bookService.addBook(bookDTO);
    }

    @PutMapping()
    public BookDTO replaceBook(@RequestBody BookDTO bookDTO, @PathVariable Long id){
        return bookService.replaceBook(bookDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.remove(id);
    }
}
