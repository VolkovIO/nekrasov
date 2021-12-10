package com.example.nekrasov.repository;

import com.example.nekrasov.entity.Author;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Boolean existsByName(@NonNull String name);

    Author findByName(String name);
}
