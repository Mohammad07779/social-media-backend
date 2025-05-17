package com.Mohammad.CareerXpert.Services;

import com.Mohammad.CareerXpert.DTOS.PostDTO;
import java.time.LocalDateTime;
import java.util.List;

public interface PostFilteringService {

    List<PostDTO> getPostsByHashtags(String hashtag);

    List<PostDTO> getPostsByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    List<PostDTO> searchPosts(String keyword);

    List<PostDTO> getSortedPosts(String sortBy);

}
