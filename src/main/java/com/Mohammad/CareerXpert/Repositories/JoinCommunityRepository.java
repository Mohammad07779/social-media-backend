package com.Mohammad.CareerXpert.Repositories;

import com.Mohammad.CareerXpert.Entities.Communities;
import com.Mohammad.CareerXpert.Entities.JoinCommunities;
import com.Mohammad.CareerXpert.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JoinCommunityRepository extends JpaRepository<JoinCommunities,Long> {

    // Check if user already joined a community
    Optional<JoinCommunities> findByUserIdAndCommunityId(Long userId, Long communityId);

    // Get all communities joined by a user
    List<JoinCommunities> findByUserId(Long userId);

    // Get all users in a community
    List<JoinCommunities> findByCommunityId(Long communityId);

    boolean existsByUserAndCommunity(Users user, Communities community);


}
