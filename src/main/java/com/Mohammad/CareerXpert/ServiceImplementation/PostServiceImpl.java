package com.Mohammad.CareerXpert.ServiceImplementation;


import com.Mohammad.CareerXpert.DTOS.PostDTO;
import com.Mohammad.CareerXpert.Entities.Post;
import com.Mohammad.CareerXpert.Entities.Users;
import com.Mohammad.CareerXpert.Exception.ResourceNotFoundException;
import com.Mohammad.CareerXpert.Exception.UnauthorizedException;
import com.Mohammad.CareerXpert.Repositories.PostRepository;
import com.Mohammad.CareerXpert.Repositories.UserRepository;
import com.Mohammad.CareerXpert.Services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, UserRepository userRepository, FileStorageService fileStorageService) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
    }


    public PostDTO createPost(PostDTO postDTO, Long currentUserId, MultipartFile file) {
        Users users = userRepository.findById(currentUserId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Post post = modelMapper.map(postDTO, Post.class);
        post.setUsers(users);
        post.setCreatedAt(LocalDateTime.now());

        if (file != null && !file.isEmpty()) {
            String fileName = fileStorageService.storeFile(file);
            String fileUrl = "/uploads/" + fileName;

            String contentType = file.getContentType();
            if (contentType != null && contentType.startsWith("image/")) {
                post.setImageUrl(fileUrl);
            } else if (contentType != null && contentType.startsWith("video/")) {
                post.setVideoUrl(fileUrl);
            }
        }

        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDTO.class);
    }


    // Get Post by ID
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        return modelMapper.map(post, PostDTO.class);

    }

    // Get Posts by User ID
    public List<PostDTO> getPostsByUserId(Long userId) {
        return postRepository.findByUsersId(userId)
                .stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
    }

    public Boolean deletePost(Long postId, Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User Not Found"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post Not Found"));

        if (!post.getUsers().getId().equals(userId)) {
            throw new UnauthorizedException("You are not authorized to delete this Post");
        }

        postRepository.delete(post);
        return true;
    }



    @Override
    public List<PostDTO> getAllPost() {
        List<Post> all = postRepository.findAll();
        return all.stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
    }


}
