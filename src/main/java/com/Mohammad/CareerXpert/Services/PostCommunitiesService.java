package com.Mohammad.CareerXpert.Services;

import com.Mohammad.CareerXpert.DTOS.PostCommunitiesDTO;

import java.util.List;

public interface PostCommunitiesService {

    PostCommunitiesDTO createPost(PostCommunitiesDTO dto, Long userId,Long communityId);
    List<PostCommunitiesDTO> getAllPostsByCommunity(Long communityId);
    List<PostCommunitiesDTO> getAllPostsByUser(Long userId);
    List<PostCommunitiesDTO> getAllPosts();


}
