package com.Mohammad.CareerXpert.Controllers;


import com.Mohammad.CareerXpert.DTOS.LikeDTO;
import com.Mohammad.CareerXpert.Services.JwtHelper;
import com.Mohammad.CareerXpert.Services.LikeService;
import com.Mohammad.CareerXpert.Utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;
    private final JwtHelper jwtHelper;

    @Autowired
    public LikeController(LikeService likeService, JwtHelper jwtHelper) {
        this.likeService = likeService;
        this.jwtHelper = jwtHelper;
    }

    @PostMapping("/{postID}")
    public ResponseEntity<ApiResponse> createLike(@PathVariable Long postID) {
        Long currentUserId = jwtHelper.getCurrentUserId();
        LikeDTO like = likeService.likePost(postID,currentUserId);
        return new ResponseEntity<>(new ApiResponse(like, "User Liked SuccessFully"), HttpStatus.OK);
    }

    @GetMapping("/totalLike/{postId}")
    public ResponseEntity<ApiResponse> getTotalLike(@PathVariable Long postId) {
        int totalLikes = likeService.getTotalLikes(postId);
        return new ResponseEntity<>(new ApiResponse(totalLikes, "Total Likes Fetched Successfully"), HttpStatus.OK);
    }

    @GetMapping("/getAllLikes/{postId}")
    public ResponseEntity<ApiResponse> getLikesByPostId(@PathVariable Long postId) {
        List<LikeDTO> likes = likeService.getLikesByPostId(postId);
        return new ResponseEntity<>(new ApiResponse(likes, "Total Likes Fetched Successfully"), HttpStatus.OK);

    }






}
