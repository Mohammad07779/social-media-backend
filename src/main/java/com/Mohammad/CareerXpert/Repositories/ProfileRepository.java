package com.Mohammad.CareerXpert.Repositories;

import com.Mohammad.CareerXpert.Entities.Profile;
import com.Mohammad.CareerXpert.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByUserName(String userName);
}
