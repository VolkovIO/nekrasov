package com.example.nekrasov.service;

import com.example.nekrasov.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> listComment();

    CommentDTO getComment(Long id);

    CommentDTO addComment(CommentDTO comment);

    CommentDTO replaceComment(CommentDTO comment, Long id);

    void remove(Long id);
}
