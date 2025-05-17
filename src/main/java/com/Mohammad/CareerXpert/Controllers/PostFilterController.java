package com.Mohammad.CareerXpert.Controllers;

import com.Mohammad.CareerXpert.DTOS.PostDTO;
import com.Mohammad.CareerXpert.Services.PostFilteringService;
import com.Mohammad.CareerXpert.Utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/post/filter")
public class PostFilterController {

    private final PostFilteringService postFilteringService;

    @Autowired
    public PostFilterController(PostFilteringService postFilteringService) {
        this.postFilteringService = postFilteringService;
    }




    // Filter by Hashtag
    @GetMapping("/hashtag/{hashtag}")
    public ResponseEntity<ApiResponse> filterByHashtag(@PathVariable String hashtag) {
        List<PostDTO> posts = postFilteringService.getPostsByHashtags(hashtag);
        return new ResponseEntity(new ApiResponse<>(posts, "Posts filtered by hashTag"), HttpStatus.OK);
    }

    //Filter by Date Range
    @GetMapping("/date")
    public ResponseEntity<ApiResponse> filterByDateRange(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        List<PostDTO> posts = postFilteringService.getPostsByDateRange(startDate, endDate);
        return new ResponseEntity(new ApiResponse<>(posts, "Posts filtered by Date"), HttpStatus.OK);
    }



    // Search by Keyword (Title or Content)
    @GetMapping("/search/{keyword}")
    public ResponseEntity<ApiResponse> searchByKeyword(@PathVariable String keyword) {
        List<PostDTO> posts = postFilteringService.searchPosts(keyword);
        return new ResponseEntity(new ApiResponse<>(posts, "Posts filtered by KeyWord"), HttpStatus.OK);
    }

    // Sorting (Newest, Oldest, Most Liked)
    @GetMapping("/sort/{sortBy}")
    public ResponseEntity<ApiResponse> sortPosts(@PathVariable String sortBy) {
        List<PostDTO> posts = postFilteringService.getSortedPosts(sortBy);
        return new ResponseEntity(new ApiResponse<>(posts, "Posts filtered by KeyWord"), HttpStatus.OK);
    }


}
