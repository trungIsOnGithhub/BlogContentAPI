package com.api.forum.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.forum.payload.CommentDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
public class Comment {
    private com.api.forum.service.CommentService service;

    public Comment(com.api.forum.service.CommentService service) {
        this.service = service;
    }

    @GetMapping
    public List<CommentDTO> getCommentsByPostId(@PathVariable(value = "postId") Long postId){
        return this.service.getCommentsByPostId(postId);
    }
    
    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@PathVariable(value = "postId") long postId,
                                                    @Valid @RequestBody CommentDTO dto){
        return new ResponseEntity<CommentDTO>(this.service.createComment(postId, dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable(value = "postId") Long postId,
                                                     @PathVariable(value = "id") Long commentId){
        CommentDTO CommentDTO = this.service.getCommentById(postId, commentId);
        return new ResponseEntity<>(CommentDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "id") Long commentId,
                                                    @Valid @RequestBody CommentDTO CommentDTO){
        CommentDTO updatedComment = this.service.updateComment(postId, commentId, CommentDTO);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId,
                                                @PathVariable(value = "id") Long commentId){
        this.service.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}