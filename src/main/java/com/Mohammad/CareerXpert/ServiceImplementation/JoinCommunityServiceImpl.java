package com.Mohammad.CareerXpert.ServiceImplementation;

import com.Mohammad.CareerXpert.DTOS.JoinCommunitiesDTO;
import com.Mohammad.CareerXpert.Entities.Communities;
import com.Mohammad.CareerXpert.Entities.JoinCommunities;
import com.Mohammad.CareerXpert.Entities.Users;
import com.Mohammad.CareerXpert.Exception.ResourceNotFoundException;
import com.Mohammad.CareerXpert.Repositories.CommunitiesRepository;
import com.Mohammad.CareerXpert.Repositories.JoinCommunityRepository;
import com.Mohammad.CareerXpert.Repositories.UserRepository;
import com.Mohammad.CareerXpert.Services.JoinCommunitiesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JoinCommunityServiceImpl implements JoinCommunitiesService {

    private final CommunitiesRepository communitiesRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final JoinCommunityRepository joinCommunityRepositoryl;

    @Autowired
    public JoinCommunityServiceImpl(CommunitiesRepository communitiesRepository, ModelMapper modelMapper, UserRepository userRepository, JoinCommunityRepository joinCommunityRepositoryl) {
        this.communitiesRepository = communitiesRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.joinCommunityRepositoryl = joinCommunityRepositoryl;
    }

    @Override
    public JoinCommunitiesDTO joinCommunity(Long communityId, Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not Found  " + userId));

        Communities community = communitiesRepository.findById(communityId)
                .orElseThrow(() -> new ResourceNotFoundException("Community Not Found" + communityId));

        boolean alreadyJoined = joinCommunityRepositoryl.existsByUserAndCommunity(user, community);
        if (alreadyJoined) {
            throw new IllegalStateException("You have already joined this community.");
        }

        JoinCommunities join = new JoinCommunities();
        join.setUser(user);
        join.setCommunity(community);
        join.setJoinedAt(LocalDateTime.now());
        JoinCommunitiesDTO dto = new JoinCommunitiesDTO();

        dto.setName(community.getName());
        dto.setCommunityId(community.getId());
        dto.setUserId(user.getId());
        dto.setId(community.getId());
        dto.setJoinedDate(community.getCreatedAt());
        JoinCommunities saved = joinCommunityRepositoryl.save(join);
        return dto;
    }


    @Override
    public void leaveCommunity(Long communityId, Long userId) {
        JoinCommunities join = joinCommunityRepositoryl.findByUserIdAndCommunityId(userId, communityId)
                .orElseThrow(() -> new ResourceNotFoundException("JoinCommunity record not found"));
        joinCommunityRepositoryl.delete(join);
    }


    @Override
    public List<JoinCommunitiesDTO> getUserJoinedCommunities(Long userId) {

        List<Communities> community = communitiesRepository.findByCreatedById(userId);


        return joinCommunityRepositoryl.findByUserId(userId).stream()
                .map(j -> {
                    JoinCommunitiesDTO dto = new JoinCommunitiesDTO();
                    dto.setId(j.getId());
                    dto.setUserId(j.getUser().getId());
                    dto.setCommunityId(j.getCommunity().getId());
                    dto.setName(j.getCommunity().getName());
                    dto.setJoinedDate(j.getJoinedAt());
                    dto.setLogourl(j.getCommunity().getLogoUrl());

                    return dto;
                })
                .collect(Collectors.toList());
    }

}
