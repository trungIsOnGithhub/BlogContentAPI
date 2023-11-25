package com.api.forum.controller;

import com.api.forum.payload.CategoryDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trial/categories")
public class CategoryController {
    private com.api.forum.service.CategoryService service;

    public CategoryController(com.api.forum.service.CategoryService service) {
        this.service = service;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO CategoryDTO){
        CategoryDTO savedCategory = this.service.addCategory(CategoryDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") Long categoryId){
         CategoryDTO CategoryDTO = this.service.getCategory(categoryId);
         return ResponseEntity.ok(CategoryDTO);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        return ResponseEntity.ok(this.service.getAllCategories());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO CategoryDTO,
                                                      @PathVariable("id") Long categoryId){
        return ResponseEntity.ok(this.service.updateCategory(CategoryDTO, categoryId));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN_1')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
        this.service.deleteCategory(categoryId);
        return ResponseEntity.ok("Category deleted successfully!.");
    }
}