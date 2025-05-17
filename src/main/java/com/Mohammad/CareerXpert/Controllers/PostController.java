package com.Mohammad.CareerXpert.Controllers;

import com.Mohammad.CareerXpert.DTOS.PostDTO;
import com.Mohammad.CareerXpert.Services.JwtHelper;
import com.Mohammad.CareerXpert.Services.PostService;
import com.Mohammad.CareerXpert.Utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final JwtHelper jwtHelper;

    @Autowired
    public PostController(PostService postService, JwtHelper jwtHelper) {
        this.postService = postService;
        this.jwtHelper = jwtHelper;
    }

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<?> createPost(
            @RequestPart(value = "post", required = false) PostDTO postDTO,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        Long currentUserId = jwtHelper.getCurrentUserId();
        return ResponseEntity.ok(postService.createPost(postDTO, currentUserId, file));
    }


    @GetMapping("/postid/{postId}")
    public ResponseEntity<ApiResponse> getPostById(@PathVariable Long postId) {
        PostDTO post = postService.getPostById(postId);
        return new ResponseEntity<>(new ApiResponse(post, "Post Found Successfully"), HttpStatus.OK);
    }

    @GetMapping("/user/posts")
    public ResponseEntity<ApiResponse> getPostsByCurrentUser() {
        Long currentUserId = jwtHelper.getCurrentUserId();
        List<PostDTO> allPost = postService.getPostsByUserId(currentUserId);
        return new ResponseEntity<>(new ApiResponse(allPost, "Posts Found Successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId) {
        Long currentUserId = jwtHelper.getCurrentUserId();
        boolean isDeleted = postService.deletePost(postId, currentUserId);
        if (isDeleted) {
            return new ResponseEntity<>(new ApiResponse(null, "Post Deleted Successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(null, "You are not authorized to delete this post"), HttpStatus.FORBIDDEN);
    }



    @GetMapping()
    public ResponseEntity<ApiResponse> getAllPost() {
        List<PostDTO> allPost = postService.getAllPost();
        return new ResponseEntity<>(new ApiResponse(allPost, "All Posts Filtered Successfully"), HttpStatus.OK);
    }




}
