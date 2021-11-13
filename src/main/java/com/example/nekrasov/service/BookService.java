package com.example.nekrasov.service;

import com.example.nekrasov.dto.BookDTO;
import com.example.nekrasov.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<BookDTO> listBook();
    Book getBook(Long id);
    Book addBook(Book book);
    Book replaceBook(Book book, Long id);
    void remove(Long id);
}
