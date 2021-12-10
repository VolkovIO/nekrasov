package com.example.nekrasov.factory;

import com.example.nekrasov.dto.CommentDTO;
import com.example.nekrasov.entity.Comment;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class CommentDTOFactory {

    public CommentDTO createCommentDTO(Comment entity) {
        return CommentDTO.builder()
                .id(entity.getId())
                .comment(entity.getComment())
                .book(entity.getBook().getName())
                .build();
    }

    public List<CommentDTO> createCommentDTOList(List<Comment> entities) {
        return entities.stream()
                .map(this::createCommentDTO)
                .collect(Collectors.toList());
    }
}
