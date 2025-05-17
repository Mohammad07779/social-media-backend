package com.Mohammad.CareerXpert.Repositories;

import com.Mohammad.CareerXpert.Entities.Communities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommunitiesRepository  extends JpaRepository<Communities,Long> {

    // Find all communities created by a specific user
    List<Communities> findByCreatedById(Long userId);

    // Search by community name
    List<Communities> findByNameContainingIgnoreCase(String keyword);

    Optional<Communities> findByNameIgnoreCase(String name);



}
