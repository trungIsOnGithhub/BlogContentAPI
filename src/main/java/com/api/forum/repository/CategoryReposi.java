package com.api.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.forum.payload.CategoryAndNumPostDTO;
import jakarta.transaction.Transactional;

public interface CategoryReposi extends JpaRepository<com.api.forum.entity.Category, Long> {
    /**
     * @param userId
     * @return
     */
    @Transactional
    @Query(
        nativeQuery = true,
        value = "SELECT categories.name as categoryName, COUNT(post.id) AS postCount FROM"
                + " categories, post"
                + " WHERE categories.id = post.category_id"
                + " AND post.user_id = :userId"
                + " GROUP BY category_id",
        name = "get_num_post_by_category_for_targeted_user"
    )
    List<CategoryAndNumPostDTO> getNumPostByCategory(Long userId);
    // no paging
}