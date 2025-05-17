package com.Mohammad.CareerXpert.Services;


import com.Mohammad.CareerXpert.DTOS.CommentDTO;

import java.util.List;

public interface CommentService {


    CommentDTO createComment(CommentDTO commentDTO,Long userId,Long postId);

    List<CommentDTO> getCommentsByPostId(Long postId);

    int getTotalComments(Long postId);

    List<CommentDTO> getAllCommentByuserId(Long userId);

}
