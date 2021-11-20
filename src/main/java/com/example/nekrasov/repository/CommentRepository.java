package com.example.nekrasov.repository;

import com.example.nekrasov.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByComment(String comment);
}
