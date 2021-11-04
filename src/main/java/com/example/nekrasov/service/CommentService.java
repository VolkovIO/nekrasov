package com.example.nekrasov.service;

import com.example.nekrasov.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> listComment();
    Comment getComment(Long id);
    Comment addComment(Comment comment);
    Comment replaceComment(Comment comment, Long id);
    void remove(Long id);
}
