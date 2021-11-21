package com.example.nekrasov.service;

import com.example.nekrasov.dto.BookDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<BookDTO> listBook();
    BookDTO getBook(Long id);
    BookDTO addBook(String name, String genre, String[] authors);
    BookDTO replaceBook(BookDTO book, Long id);
    void remove(Long id);
}
