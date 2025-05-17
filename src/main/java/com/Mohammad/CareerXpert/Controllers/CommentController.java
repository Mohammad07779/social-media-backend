package com.Mohammad.CareerXpert.Controllers;


import com.Mohammad.CareerXpert.DTOS.CommentDTO;
import com.Mohammad.CareerXpert.Services.CommentService;
import com.Mohammad.CareerXpert.Services.JwtHelper;
import com.Mohammad.CareerXpert.Utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final JwtHelper jwtHelper;

    @Autowired
    public CommentController(CommentService commentService, JwtHelper jwtHelper) {
        this.commentService = commentService;
        this.jwtHelper = jwtHelper;
    }

    // Create Comment
    @PostMapping("/{postId}")
    public ResponseEntity<ApiResponse> createComment(@RequestBody CommentDTO dto,@PathVariable Long postId) {
        Long currentUserId = jwtHelper.getCurrentUserId();
        CommentDTO comment = commentService.createComment(dto,currentUserId,postId);
        return new ResponseEntity<>(new ApiResponse(comment, "Comment Created Successfully"), HttpStatus.OK);
    }

    // Get Comments By PostId
    @GetMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> getCommentByPostId(@PathVariable Long postId) {
        List<CommentDTO> comment = commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(new ApiResponse(comment, "All Comments Found Successfully"), HttpStatus.OK);
    }

    // Get Total Comments Count
    @GetMapping("/totalComment/{postId}")
    public ResponseEntity<ApiResponse> getTotalComments(@PathVariable Long postId) {
        int totalComment = commentService.getTotalComments(postId);
        return new ResponseEntity<>(new ApiResponse(totalComment, "Total Comments Fetched Successfully"), HttpStatus.OK);
    }

    @GetMapping("/getOwnComment")
    public ResponseEntity<ApiResponse> getTotalCommentByUserId() {
        Long currentUserId = jwtHelper.getCurrentUserId();
        List<CommentDTO> allComment = commentService.getAllCommentByuserId(currentUserId);
        return new ResponseEntity<>(new ApiResponse(allComment, "Total Comments Fetched Successfully"), HttpStatus.OK);
    }
}





