package com.Mohammad.CareerXpert.Services;


import com.Mohammad.CareerXpert.DTOS.PostDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO, Long currentUserId, MultipartFile file);

    PostDTO getPostById(Long id);

    List<PostDTO> getPostsByUserId(Long userId);

     Boolean deletePost(Long postId,Long userId);

    List<PostDTO> getAllPost();
}
