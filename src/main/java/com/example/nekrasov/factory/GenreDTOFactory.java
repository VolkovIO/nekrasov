package com.example.nekrasov.factory;

import com.example.nekrasov.dto.GenreDTO;
import com.example.nekrasov.entity.Genre;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class GenreDTOFactory {

    public GenreDTO createGenreDTO(Genre entity){
        return GenreDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public List<GenreDTO> createGenreDTOList(List<Genre> entities){
        return entities.stream()
                .map(this::createGenreDTO)
                .collect(Collectors.toList());
    }
}
