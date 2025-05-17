package com.Mohammad.CareerXpert.ServiceImplementation;

import com.Mohammad.CareerXpert.DTOS.PostCommunitiesDTO;
import com.Mohammad.CareerXpert.Entities.Communities;
import com.Mohammad.CareerXpert.Entities.PostCommunities;
import com.Mohammad.CareerXpert.Entities.Users;
import com.Mohammad.CareerXpert.Exception.ResourceNotFoundException;
import com.Mohammad.CareerXpert.Repositories.CommunitiesRepository;
import com.Mohammad.CareerXpert.Repositories.PostCommunityRepository;
import com.Mohammad.CareerXpert.Repositories.UserRepository;
import com.Mohammad.CareerXpert.Services.PostCommunitiesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostCommunitiesServiceImpl implements PostCommunitiesService {

    private final CommunitiesRepository communitiesRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PostCommunityRepository postCommunityRepository;

    @Autowired
    public PostCommunitiesServiceImpl(CommunitiesRepository communitiesRepository, ModelMapper modelMapper, UserRepository userRepository, PostCommunityRepository postCommunityRepository) {
        this.communitiesRepository = communitiesRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.postCommunityRepository = postCommunityRepository;
    }

    @Override
    public PostCommunitiesDTO createPost(PostCommunitiesDTO dto, Long userId,Long CommunityId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found " + userId));

        Communities community = communitiesRepository.findById(CommunityId).orElseThrow(() -> new ResourceNotFoundException("Community Not Found With" + dto.getCommunityId()));

        PostCommunities post = modelMapper.map(dto, PostCommunities.class);
        post.setPostedBy(user);
        post.setCommunity(community);
        post.setCreatedDate(LocalDateTime.now());

        PostCommunities saved = postCommunityRepository.save(post);
        PostCommunitiesDTO postDto = modelMapper.map(saved, PostCommunitiesDTO.class);
        postDto.setPostedByUserId(saved.getId());
        return postDto;
    }

    @Override
    public List<PostCommunitiesDTO> getAllPostsByCommunity(Long communityId) {
        return postCommunityRepository.findByCommunityId(communityId).stream().map(p -> modelMapper.map(p, PostCommunitiesDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostCommunitiesDTO> getAllPostsByUser(Long userId) {
        return postCommunityRepository.findByPostedById(userId).stream().map(p -> modelMapper.map(p, PostCommunitiesDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostCommunitiesDTO> getAllPosts() {
        return postCommunityRepository.findAll()
                .stream().map(p ->
                        modelMapper.map(p, PostCommunitiesDTO.class))
                .collect(Collectors.toList());
    }
}
