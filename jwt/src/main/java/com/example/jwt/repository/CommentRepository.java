package com.example.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jwt.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
