package com.Mohammad.CareerXpert.ServiceImplementation;

import com.Mohammad.CareerXpert.DTOS.CommentDTO;
import com.Mohammad.CareerXpert.DTOS.PostDTO;
import com.Mohammad.CareerXpert.Entities.Comment;
import com.Mohammad.CareerXpert.Entities.Post;
import com.Mohammad.CareerXpert.Entities.Users;
import com.Mohammad.CareerXpert.Exception.ResourceNotFoundException;
import com.Mohammad.CareerXpert.Repositories.CommentRepository;
import com.Mohammad.CareerXpert.Repositories.PostRepository;
import com.Mohammad.CareerXpert.Repositories.ProfileRepository;
import com.Mohammad.CareerXpert.Repositories.UserRepository;
import com.Mohammad.CareerXpert.Services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapperl;
    private final UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapperl, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapperl = modelMapperl;
        this.userRepository = userRepository;

    }

    public CommentDTO createComment(CommentDTO commentDTO, Long userId, Long postId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found With" + userId));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post Not Found With userId " + userId));
        Comment comment = modelMapperl.map(commentDTO, Comment.class);

        comment.setUser(user);
        comment.setPost(post);
        comment.setCommentedAt(LocalDateTime.now());
        Comment save = commentRepository.save(comment);
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setUserId(comment.getUser().getId());
        dto.setUserName(comment.getUser().getUserName());
        dto.setPostId(postId);
        dto.setContent(comment.getContent());
        dto.setCommentedAt(comment.getCommentedAt());
        return dto;

    }
    public List<CommentDTO> getCommentsByPostId(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        List<Comment> comments = commentRepository.findByPost(post);

        return comments.stream().map(comment -> {
            CommentDTO dto = new CommentDTO();
            dto.setId(comment.getId());
            dto.setUserId(comment.getUser().getId());
            dto.setUserName(comment.getUser().getUserName());
            dto.setPostId(postId);
            dto.setContent(comment.getContent());
            dto.setCommentedAt(comment.getCommentedAt());
            return dto;
        }).collect(Collectors.toList());
    }


    public int getTotalComments(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        List<Comment> comments = commentRepository.findByPost(post);
        return comments.size();
    }

    public List<CommentDTO> getAllCommentByuserId(Long userId){
        Users users = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No Comment found"));
        List<Comment> comments = commentRepository.findByUserId(users.getId());
        return comments.stream()
                .map(comment -> modelMapperl.map(comment, CommentDTO.class))
                .collect(Collectors.toList());

    }

}
