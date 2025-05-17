package com.Mohammad.CareerXpert.Controllers;

import com.Mohammad.CareerXpert.DTOS.JoinCommunitiesDTO;
import com.Mohammad.CareerXpert.Services.JoinCommunitiesService;
import com.Mohammad.CareerXpert.Services.JwtHelper;
import com.Mohammad.CareerXpert.Utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/joinCommunity")
public class JoinCommunityController {

    private final JoinCommunitiesService joinCommunitiesServicel;
    private final JwtHelper jwtHelper;

    @Autowired
    public JoinCommunityController(JoinCommunitiesService joinCommunitiesServicel, JwtHelper jwtHelper) {
        this.joinCommunitiesServicel = joinCommunitiesServicel;
        this.jwtHelper = jwtHelper;
    }


    @PostMapping("/{communityId}")
    public ResponseEntity<ApiResponse> joinCommunity(@PathVariable Long communityId) {
        Long currentUserId = jwtHelper.getCurrentUserId();
        JoinCommunitiesDTO joinCommunities = joinCommunitiesServicel.joinCommunity(communityId, currentUserId);
        return new ResponseEntity<>(new ApiResponse<>(joinCommunities, "Joined With Community SuccessFully"), HttpStatus.CREATED);

    }

    @DeleteMapping("/{communityId}")
    public ResponseEntity<ApiResponse> leaveCommunity(@PathVariable Long communityId) {
        Long currentUserId = jwtHelper.getCurrentUserId();
        joinCommunitiesServicel.leaveCommunity(communityId, currentUserId);
        ResponseEntity.ok("Left community successfully.");
        return new ResponseEntity<>(new ApiResponse<>(null, "Leave With Community SuccessFully"), HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse> getUserJoinedCommunities() {
        Long currentUserId = jwtHelper.getCurrentUserId();
        List<JoinCommunitiesDTO> userJoinedCommunities = joinCommunitiesServicel.getUserJoinedCommunities(currentUserId);
        return new ResponseEntity<>(new ApiResponse<>(userJoinedCommunities, "Found All JoinedCommunities Community SuccessFully"), HttpStatus.OK);
    }



}
