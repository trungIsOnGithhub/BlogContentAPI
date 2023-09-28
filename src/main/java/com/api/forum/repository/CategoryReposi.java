package com.api.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryReposi extends JpaRepository<com.api.forum.entity.Category,Long> {}