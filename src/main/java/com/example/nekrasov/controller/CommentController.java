package com.example.nekrasov.controller;

import com.example.nekrasov.entity.Comment;
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
    List<Comment> fetchComments(){
        return commentService.listComment();
    }

    @GetMapping("/{id}")
    Comment fetchComment(@PathVariable Long id){
        return commentService.getComment(id);
    }

    @PostMapping
    Comment addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    @PutMapping("/{id}")
    Comment replaceComment(@RequestBody Comment comment, @PathVariable Long id){
        return commentService.replaceComment(comment, id);
    }

    @DeleteMapping("/{id}")
    void deleteComment(@PathVariable Long id){
        commentService.remove(id);
    }
}
