package com.api.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReposi extends JpaRepository<com.api.forum.entity.User, Long> {
    Optional<com.api.forum.entity.User> findByEmail(String email);
    Optional<com.api.forum.entity.User> findByUsernameOrEmail(String username, String email);
    Optional<com.api.forum.entity.User> findByUsername(String username);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}