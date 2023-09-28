package com.api.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentReposi extends JpaRepository<com.api.forum.entity.Comment, Long> {
    List<com.api.forum.entity.Comment> findByPostId(long postId);
}