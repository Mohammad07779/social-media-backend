package com.Mohammad.CareerXpert.ServiceImplementation;


import com.Mohammad.CareerXpert.DTOS.LikeDTO;
import com.Mohammad.CareerXpert.Entities.Like;
import com.Mohammad.CareerXpert.Entities.Post;
import com.Mohammad.CareerXpert.Entities.Users;
import com.Mohammad.CareerXpert.Exception.ResourceNotFoundException;
import com.Mohammad.CareerXpert.Repositories.LikeRepository;
import com.Mohammad.CareerXpert.Repositories.PostRepository;
import com.Mohammad.CareerXpert.Repositories.UserRepository;
import com.Mohammad.CareerXpert.Services.LikeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {

    private final ModelMapper modelMapper;
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public LikeServiceImpl(ModelMapper modelMapper, LikeRepository likeRepository, PostRepository postRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public LikeDTO likePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Optional<Like> likeExist = likeRepository.findByUserIdAndPost(userId, post);

        if (likeExist.isPresent()) {
            likeRepository.delete(likeExist.get());
            Like like = new Like();
            like.setUser(user);
            like.setPost(post);
            like.setLikedAt(LocalDateTime.now());
            return modelMapper.map(like, LikeDTO.class);
        } else {
            Like like = new Like();
            like.setUser(user);
            like.setPost(post);
            like.setLikedAt(LocalDateTime.now());

            Like save = likeRepository.save(like);
            return modelMapper.map(save, LikeDTO.class);
        }
    }


    public List<LikeDTO> getLikesByPostId(Long postId) {

        List<Like> likes = likeRepository.getLikesByPostId(postId);

        return likes.stream()
                .map(like -> modelMapper.map(like, LikeDTO.class))
                .collect(Collectors.toList());
    }


    public int getTotalLikes(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        List<Like> likes = likeRepository.findByPost(post);
        return likes.size();
    }


}
