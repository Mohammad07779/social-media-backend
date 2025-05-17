package com.Mohammad.CareerXpert.Repositories;

import com.Mohammad.CareerXpert.DTOS.PostDTO;
import com.Mohammad.CareerXpert.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUsersId(Long userId);


    List<Post> findByHashTagsContaining(String hashtag);

    List<PostDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Post> searchPosts(@Param("keyword") String keyword);

    List<PostDTO> findAllByOrderByCreatedAtDesc();

    List<PostDTO> findAllByOrderByCreatedAtAsc();

    @Query("SELECT p FROM Post p LEFT JOIN p.likes l GROUP BY p.postId ORDER BY COUNT(l) DESC")
    List<Post> findAllByOrderByTotalLikesDesc();


}



