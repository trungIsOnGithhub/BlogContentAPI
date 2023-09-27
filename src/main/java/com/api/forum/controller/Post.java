package com.api.forum.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.forum.payload.CommentDTO;
import com.api.forum.payload.PostDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class Post {
	private com.api.forum.service.Post service;
	
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(value = "postId") Long postId){
        return new ResponseEntity<PostDTO>(this.service.getPostById(postId), HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO PostDTO, @PathVariable(name = "postId") long id){

       PostDTO postResponse = this.service.updatePost(PostDTO, id);

       return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

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

