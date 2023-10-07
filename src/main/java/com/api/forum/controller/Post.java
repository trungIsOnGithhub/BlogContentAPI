package com.api.forum.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.forum.constants.App;
import com.api.forum.payload.CommentDTO;
import com.api.forum.payload.PostDTO;
import com.api.forum.payload.PostDTOPaged;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class Post {
	private com.api.forum.service.PostService service;

    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping
    public PostDTOPaged getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = App.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = App.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = App.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = App.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return this.service.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @ApiResponse(
        responseCode = "200",
        description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(value = "postId") Long postId){
        return new ResponseEntity<PostDTO>(this.service.getPostById(postId), HttpStatus.OK);
    }

  
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    // create blog post rest api
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO dto){
        return new ResponseEntity<PostDTO>(this.service.createPost(dto), HttpStatus.CREATED);
    }


    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO PostDTO, @PathVariable(name = "postId") long id){

       PostDTO postResponse = this.service.updatePost(PostDTO, id);

       return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @ApiResponse(
        responseCode = "200",
        description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "postId") long id){

        this.service.deletePostById(id);

        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable(name = "categoryId") Long categoryId){
        List<PostDTO> PostDTOs = this.service.getPostsByCategory(categoryId);
        return ResponseEntity.ok(PostDTOs);
    }
}

