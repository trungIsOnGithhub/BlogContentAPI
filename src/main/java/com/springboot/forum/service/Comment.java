package com.api.forum.service;

import com.api.forum.payload.CommentDTO;

import java.util.List;

public interface Comment {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);
    CommentDto getCommentById(Long postId, Long commentId);
    CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest);
    void deleteComment(Long postId, Long commentId);
}