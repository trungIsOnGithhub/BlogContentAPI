package com.api.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<com.api.forum.entity.Category,Long> {}