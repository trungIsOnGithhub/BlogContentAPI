package com.api.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.forum.entity.User;

public interface PostReposi extends JpaRepository<com.api.forum.entity.Post, Long> {
    List<com.api.forum.entity.Post> findByCategoryId(Long categoryId);
    List<com.api.forum.entity.User> findByOwnerId(Long ownerId);
}