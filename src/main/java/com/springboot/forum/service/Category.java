package com.api.forum.service;

import com.api.forum.payload.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO addCategory(CategoryDTO CategoryDTO);
    CategoryDTO getCategory(Long id);
    List<CategoryDTO> getAllCategories();
    CategoryDTO updateCategory(CategoryDTO CategoryDTO, Long id);
    void deleteCategory(Long id);
}