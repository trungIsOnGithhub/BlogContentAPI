package com.api.forum.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.api.forum.entity.Category;
import com.api.forum.entity.Post;
import com.api.forum.exception.types.NotFoundException;
import com.api.forum.payload.PostDTO;
import com.api.forum.payload.PostDTOPaged;
import com.api.forum.repository.CategoryReposi;
import com.api.forum.repository.PostReposi;
import com.api.forum.service.PostService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostReposi postRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CategoryReposi categoryRepository;

    public PostDTO createPost(PostDTO postDto) {
        Category category = categoryRepository.findById(postDto.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Category", "id", postDto.getCategoryId()));

        Post post = mapper.map(postDto, Post.class);
        post.setCategory(category);

        postRepository.save(post);

        return postDto;
    }

    public PostDTOPaged getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = this.postRepository.findAll(pageable);

        // List<Post> listOfPosts = posts.getContent();

        List<PostDTO> content= posts.getContent().stream()
                                    .map( post -> mapper.map(post, PostDTO.class) ).collect(Collectors.toList());

        PostDTOPaged pagedResponse = new PostDTOPaged(
                        content,
                        posts.getNumber(), posts.getSize(), posts.getTotalElements(), posts.getTotalPages(),
                        posts.isLast()
                    );

        return pagedResponse;
    }

    public PostDTO getPostById(long postId) {
        Post post = postRepository.findById(postId)
                            .orElseThrow( () -> new NotFoundException("Post", "id", postId) );
        return mapper.map(post, PostDTO.class);
    }

    public PostDTO updatePost(PostDTO postDTO, long postId) {
        long categoryIdOfThisPost = postDTO.getCategoryId();

        Post post = postRepository.findById(postId)
                            .orElseThrow( () -> new NotFoundException("Post", "id", postId) );

        Category category = categoryRepository.findById(postDTO.getCategoryId())
                            .orElseThrow( () -> new NotFoundException("Category", "id", categoryIdOfThisPost) );

        post.setTitle(postDTO.getTitle());
        // post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        post.setCategory(category);

        postRepository.save(post);

        return postDTO;
    }

    public void deletePostById(long id) {
        Post post = postRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    public List<PostDTO> getPostsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                                .orElseThrow(() -> new NotFoundException("Category", "id", categoryId));

        List<Post> posts = postRepository.findByCategoryId(categoryId);

        return posts.stream()
                    .map( (post) -> mapper.map(post, PostDTO.class) ).collect(Collectors.toList());
    }
}
