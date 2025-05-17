package com.Mohammad.CareerXpert.ServiceImplementation;

import com.Mohammad.CareerXpert.DTOS.PostDTO;
import com.Mohammad.CareerXpert.Entities.Post;
import com.Mohammad.CareerXpert.Repositories.PostRepository;
import com.Mohammad.CareerXpert.Services.PostFilteringService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostFilteringImpl implements PostFilteringService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PostFilteringImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<PostDTO> getPostsByHashtags(String hashtag) {
        List<Post> posts = postRepository.findByHashTagsContaining(hashtag);
        return posts.stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getPostsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return postRepository.findByCreatedAtBetween(startDate, endDate);
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {
        List<Post> posts = postRepository.searchPosts(keyword);
        return posts.stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getSortedPosts(String sortBy) {
        if (sortBy.equalsIgnoreCase("newest")) {
            return postRepository.findAllByOrderByCreatedAtDesc();
        } else if (sortBy.equalsIgnoreCase("oldest")) {
            return postRepository.findAllByOrderByCreatedAtAsc();
        } else if (sortBy.equalsIgnoreCase("mostLiked")) {
            List<Post> post = postRepository.findAllByOrderByTotalLikesDesc();
            return post.stream()
                    .map(posts -> modelMapper.map(posts, PostDTO.class))
                    .collect(Collectors.toList());
        }
        List<Post> all = postRepository.findAll();
        return all.stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
    }
}
