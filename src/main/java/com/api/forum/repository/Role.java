package com.api.forum.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Role extends JpaRepository<com.api.forum.entity.Role, Long> {
    Optional<com.api.forum.entity.Role> findByName(String name);
}