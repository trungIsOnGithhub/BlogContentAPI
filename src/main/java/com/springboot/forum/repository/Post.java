package com.api.forum.repository;

import com.api.forum.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategoryId(Long categoryId);
    List<User> findByOwnerId(Long ownerId);
}