package com.example.nekrasov.service;

import com.example.nekrasov.dto.CommentDTO;
import com.example.nekrasov.entity.Book;
import com.example.nekrasov.entity.Comment;
import com.example.nekrasov.excepsion.NotFoundException;
import com.example.nekrasov.factory.CommentDTOFactory;
import com.example.nekrasov.repository.BookRepository;
import com.example.nekrasov.repository.CommentRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentDTOFactory commentDTOFactory;
    private final BookRepository bookRepository;

    @Override
    public List<CommentDTO> listComment() {
        List<Comment> all = commentRepository.findAll();
        return commentDTOFactory.createCommentDTOList(all);
    }

    @Override
    public CommentDTO getComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Комментарий с идентификатором \"%s\" не найден.", id)));
        return commentDTOFactory.createCommentDTO(comment);
    }

    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {
        //TODO Добавить проверки исключения
        Book bookByName = bookRepository.findBookByName(commentDTO.getBook());

        Comment newComment = Comment.builder()
                .comment(commentDTO.getComment())
                .book(bookByName)
                .build();

        Comment saveComment = commentRepository.save(newComment);
        return commentDTOFactory.createCommentDTO(saveComment);
    }

    @Override
    public CommentDTO replaceComment(CommentDTO commentDTO, Long id) {
        //TODO Добавить проверки, исключения
        Comment comment1 = commentRepository.findById(id)
                .map(comment -> {
                    comment.setComment(commentDTO.getComment());
                    comment.setBook(bookRepository.findBookByName(commentDTO.getBook()));
                    return commentRepository.save(comment);
                })
                .orElseGet(() -> {
//                    newComment.setId(id);
                    return commentRepository.save(new Comment());
                });
        return commentDTOFactory.createCommentDTO(comment1);
    }

    @Override
    public void remove(Long id) {
        commentRepository.deleteById(id);
    }
}
