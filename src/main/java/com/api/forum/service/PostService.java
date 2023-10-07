package com.api.forum.service;

import com.api.forum.payload.PostDTO;
import com.api.forum.payload.PostDTOPaged;

import java.util.List;

public interface Post {
    PostDTO createPost(PostDTO postDTO);
    PostDTOPaged getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDTO getPostById(long id);
    PostDTO updatePost(PostDTO postDTO, long id);
    void deletePostById(long id);
    List<PostDTO> getPostsByCategory(Long categoryId);
}