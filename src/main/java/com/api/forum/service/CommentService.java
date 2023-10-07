package com.api.forum.service;

import com.api.forum.payload.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(long postId, CommentDTO commentDto);
    List<CommentDTO> getCommentsByPostId(long postId);
    CommentDTO getCommentById(Long postId, Long commentId);
    CommentDTO updateComment(Long postId, long commentId, CommentDTO commentRequest);
    void deleteComment(Long postId, Long commentId);
}