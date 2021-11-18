package com.example.nekrasov.controller;

import com.example.nekrasov.dto.CommentDTO;
import com.example.nekrasov.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    List<CommentDTO> fetchComments(){
        return commentService.listComment();
    }

    @GetMapping("/{id}")
    CommentDTO fetchComment(@PathVariable Long id){
        return commentService.getComment(id);
    }

    @PostMapping
    CommentDTO addComment(@RequestBody CommentDTO comment){
        return commentService.addComment(comment);
    }

    @PutMapping("/{id}")
    CommentDTO replaceComment(@RequestBody CommentDTO comment, @PathVariable Long id){
        return commentService.replaceComment(comment, id);
    }

    @DeleteMapping("/{id}")
    void deleteComment(@PathVariable Long id){
        commentService.remove(id);
    }
}
