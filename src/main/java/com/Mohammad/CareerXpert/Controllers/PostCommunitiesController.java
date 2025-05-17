package com.Mohammad.CareerXpert.Controllers;

import com.Mohammad.CareerXpert.DTOS.PostCommunitiesDTO;
import com.Mohammad.CareerXpert.Services.JwtHelper;
import com.Mohammad.CareerXpert.Services.PostCommunitiesService;
import com.Mohammad.CareerXpert.Utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postCommunity")
public class PostCommunitiesController {

    private final PostCommunitiesService postCommunitiesService;
    private final JwtHelper jwtHelper;

    @Autowired
    public PostCommunitiesController(PostCommunitiesService postCommunitiesService, JwtHelper jwtHelper) {
        this.postCommunitiesService = postCommunitiesService;
        this.jwtHelper = jwtHelper;
    }

    @PostMapping("/{communityId}")
    public ResponseEntity<ApiResponse> createPost(@RequestBody PostCommunitiesDTO dto, @PathVariable Long communityId) {
        Long currentUserId = jwtHelper.getCurrentUserId();
        PostCommunitiesDTO post = postCommunitiesService.createPost(dto, currentUserId,communityId);
        return new ResponseEntity<>(new ApiResponse(post, "Community Create  Successfully"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllPosts() {
        List<PostCommunitiesDTO> allPosts = postCommunitiesService.getAllPosts();
        return new ResponseEntity<>(new ApiResponse(allPosts, "All Post Found  Successfully"), HttpStatus.OK);
    }


}
