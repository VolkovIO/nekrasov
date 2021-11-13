package com.example.nekrasov.factory;

import com.example.nekrasov.dto.AuthorDTO;
import com.example.nekrasov.entity.Author;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class AuthorDTOFactory {

    public AuthorDTO createAuthorDTO(Author entity){
        return AuthorDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .books(entity.getBooks())
                .build();
    }

    public List<AuthorDTO> createAuthorDTOList(List<Author> entities){
        return entities.stream()
                .map(this::createAuthorDTO)
                .collect(Collectors.toList());
    }
}
