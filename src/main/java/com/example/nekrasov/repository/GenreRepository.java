package com.example.nekrasov.repository;

import com.example.nekrasov.entity.Genre;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Boolean existsByName(@NonNull String name);

    Genre findByName(@NonNull String name);
}
