package com.api.forum.service.implementation;

import com.api.forum.entity.Comment;
import com.api.forum.entity.Post;
import com.api.forum.exception.types.APIException;
import com.api.forum.exception.types.NotFoundException;
import com.api.forum.payload.CommentDTO;
import com.api.forum.repository.CommentReposi;
import com.api.forum.repository.PostReposi;
import com.api.forum.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentReposi commentReposi;
    private PostReposi postReposi;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentReposi commentReposi, PostReposi postReposi, ModelMapper mapper) {
        this.commentReposi = commentReposi;
        this.postReposi = postReposi;
        this.mapper = mapper;
    }

    @Override
    public CommentDTO createComment(long postId, CommentDTO commentDTO) {

        Comment comment = mapToEntity(commentDTO);

        Post post = postReposi.findById(postId).orElseThrow( () -> new NotFoundException("Post", "id", postId) );

        comment.setPost(post);

        Comment newComment =  commentReposi.save(comment);

        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(long postId) {
        List<Comment> comments = commentReposi.findByPostId(postId);

        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(Long postId, Long commentId) {
        Post post = postReposi.findById(postId).orElseThrow( () -> new NotFoundException("Post", "id", postId) );

        Comment comment = commentReposi.findById(commentId).orElseThrow( () -> new NotFoundException("Comment", "id", commentId) );

        if(!comment.getPost().getId().equals(post.getId())){
            throw new APIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return mapToDTO(comment);
    }

    @Override
    public CommentDTO updateComment(Long postId, long commentId, CommentDTO commentRequest) {
        Post post = postReposi.findById(postId).orElseThrow( () -> new NotFoundException("Post", "id", postId) );

        Comment comment = commentReposi.findById(commentId).orElseThrow(() ->
                new NotFoundException("Comment", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new APIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to required post");
        }

//        comment.setName(commentRequest.getName());
        comment.setOwner(commentRequest.getOwner());
        comment.setContent(commentRequest.getContent());

        Comment updatedComment = commentReposi.save(comment);
        return mapToDTO(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        // retrieve post entity by id
        Post post = postReposi.findById(postId).orElseThrow(
                () -> new NotFoundException("Post", "id", postId));

        // retrieve comment by id
        Comment comment = commentReposi.findById(commentId).orElseThrow(() ->
                new NotFoundException("Comment", "id", commentId));

        if( !comment.getPost().getId().equals(post.getId()) ){
            throw new APIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to required post");
        }

        commentReposi.delete(comment);
    }


    private final CommentDTO mapToDTO(Comment comment){
        CommentDTO commentDTO = mapper.map(comment, CommentDTO.class);
        return  commentDTO;
    }
    private final Comment mapToEntity(CommentDTO commentDTO){
        Comment comment = mapper.map(commentDTO, Comment.class);
        return  comment;
    }
}