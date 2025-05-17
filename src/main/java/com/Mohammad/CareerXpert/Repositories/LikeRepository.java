package com.Mohammad.CareerXpert.Repositories;

import com.Mohammad.CareerXpert.Entities.Like;
import com.Mohammad.CareerXpert.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findByPost(Post post);

    @Query("SELECT l FROM Like l WHERE l.post.id = :postId")
    List<Like> getLikesByPostId(@Param("postId") Long postId);

    Optional<Like> findByUserIdAndPost(Long userId, Post post);


}