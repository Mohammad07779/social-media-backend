package com.Mohammad.CareerXpert.Services;



import com.Mohammad.CareerXpert.DTOS.LikeDTO;

import java.util.List;

public interface LikeService {

    LikeDTO likePost(Long postId, Long userId);

    int getTotalLikes(Long postId);

    List<LikeDTO> getLikesByPostId(Long postId);
}
