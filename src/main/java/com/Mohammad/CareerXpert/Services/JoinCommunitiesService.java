package com.Mohammad.CareerXpert.Services;

import com.Mohammad.CareerXpert.DTOS.JoinCommunitiesDTO;

import java.util.List;

public interface JoinCommunitiesService {

    JoinCommunitiesDTO joinCommunity(Long communityId, Long userId);
    void leaveCommunity(Long communityId, Long userId);
    List<JoinCommunitiesDTO> getUserJoinedCommunities(Long userId);


}
