package com.Mohammad.CareerXpert.Repositories;

import com.Mohammad.CareerXpert.Entities.PostCommunities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCommunityRepository extends JpaRepository<PostCommunities,Long> {

    // Get all posts in a specific community
    List<PostCommunities> findByCommunityId(Long communityId);

    // Get all posts by a user
    List<PostCommunities> findByPostedById(Long userId);

    // Sort posts by createdDate
    List<PostCommunities> findAllByOrderByCreatedDateDesc(); // Newest First
    List<PostCommunities> findAllByOrderByCreatedDateAsc();  // Oldest First
}
