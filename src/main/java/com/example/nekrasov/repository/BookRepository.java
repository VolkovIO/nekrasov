package com.example.nekrasov.repository;

import com.example.nekrasov.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookByName(String name);
}
