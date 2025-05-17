package com.Mohammad.CareerXpert.Repositories;


import com.Mohammad.CareerXpert.Entities.Comment;
import com.Mohammad.CareerXpert.Entities.Post;
import com.Mohammad.CareerXpert.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);

    long countByPost(Post post);
    
    List<Comment> findByUserId(Long userId);
}
