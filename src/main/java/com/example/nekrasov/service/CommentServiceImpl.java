package com.example.nekrasov.service;

import com.example.nekrasov.entity.Comment;
import com.example.nekrasov.excepsion.NotFoundException;
import com.example.nekrasov.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> listComment() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getComment(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Комментарий с идентификатором \"%s\" не найден.", id)));
    }

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment replaceComment(Comment newComment, Long id) {
        return commentRepository.findById(id)
                .map(comment -> {
                    comment.setId(newComment.getId());
                    comment.setComment(newComment.getComment());
                    comment.setBook(newComment.getBook());
                    return commentRepository.save(comment);
                })
                .orElseGet(() -> {
                    newComment.setId(id);
                    return commentRepository.save(newComment);
                });
    }

    @Override
    public void remove(Long id) {
        commentRepository.deleteById(id);
    }
}
