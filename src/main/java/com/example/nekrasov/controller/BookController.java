package com.example.nekrasov.controller;

import com.example.nekrasov.entity.Book;
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
    public List<Book> fetchBooks(){
        return bookService.listBook();
    }

    @GetMapping("/{id}")
    public Book fetchBook(@PathVariable Long id){
        return bookService.getBook(id);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @PutMapping()
    public Book replaceBook(@RequestBody Book book, @PathVariable Long id){
        return bookService.replaceBook(book, id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.remove(id);
    }
}
