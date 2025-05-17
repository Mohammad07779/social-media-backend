package com.Mohammad.CareerXpert.Controllers;

import com.Mohammad.CareerXpert.DTOS.ProfileDTO;
import com.Mohammad.CareerXpert.ServiceImplementation.ProfileServiceImpl;
import com.Mohammad.CareerXpert.Services.ProfileService;
import com.Mohammad.CareerXpert.Utility.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileServiceImpl profileService;
    @Autowired
    public ProfileController(ProfileServiceImpl profileService) {
        this.profileService = profileService;
    }


    @PutMapping(value = "/{userName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> updateUserDetails(
            @PathVariable String userName,
            @RequestPart("dto") String dtoJson, // Accepting as String to parse later
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
            @RequestPart(value = "bannerImage", required = false) MultipartFile bannerImage) throws JsonProcessingException {

        // Deserialize the ProfileDTO from JSON string
        ProfileDTO dto = new ObjectMapper().readValue(dtoJson, ProfileDTO.class);

        // Proceed with the rest of your logic
        ProfileDTO updatedProfile = profileService.updateUserDetails(dto, userName, profileImage, bannerImage);
        return new ResponseEntity<>(new ApiResponse(updatedProfile, "User Updated Successfully"), HttpStatus.OK);
    }


    @GetMapping("/{userName}")
    public ResponseEntity<ApiResponse> getProfile(@PathVariable String userName) {
        ProfileDTO profile = profileService.getProfile(userName);
        return new ResponseEntity<>(new ApiResponse(profile, "profile found SuccessFully"), HttpStatus.OK);
    }


}
