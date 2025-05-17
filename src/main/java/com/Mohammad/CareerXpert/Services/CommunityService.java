package com.Mohammad.CareerXpert.Services;

import com.Mohammad.CareerXpert.DTOS.CreateCommunitiesDTO;

import java.util.List;

public interface CommunityService {

    CreateCommunitiesDTO createCommunity(CreateCommunitiesDTO dto, Long userId);
    List<CreateCommunitiesDTO> getAllCommunities();
    List<CreateCommunitiesDTO> getCommunitiesByUser(Long userId);
    CreateCommunitiesDTO getCommunityById(Long id);

    CreateCommunitiesDTO getCommunityByName(String name);
}
