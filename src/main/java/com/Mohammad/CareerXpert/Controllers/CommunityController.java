package com.Mohammad.CareerXpert.Controllers;

import com.Mohammad.CareerXpert.DTOS.CreateCommunitiesDTO;
import com.Mohammad.CareerXpert.Services.CommunityService;
import com.Mohammad.CareerXpert.Services.JwtHelper;
import com.Mohammad.CareerXpert.Utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Community")
public class CommunityController {


    private final JwtHelper jwtHelper;
    private final CommunityService communityService;

    @Autowired
    public CommunityController(JwtHelper jwtHelper, CommunityService communityService) {
        this.jwtHelper = jwtHelper;
        this.communityService = communityService;
    }


    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ApiResponse> createCommunity(
            @RequestPart("community") CreateCommunitiesDTO dto,
            @RequestPart(value = "logo", required = false) MultipartFile logo) {

        Long currentUserId = jwtHelper.getCurrentUserId();


        if (logo != null && !logo.isEmpty()) {
            String logoUrl =uploadCommunityImage(logo);
            dto.setLogoUrl(logoUrl);
        }

        CreateCommunitiesDTO saved = communityService.createCommunity(dto, currentUserId);
        return new ResponseEntity<>(new ApiResponse(saved, "Community Create Successfully"), HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCommunities() {
        List<CreateCommunitiesDTO> allCommunities = communityService.getAllCommunities();
        return new ResponseEntity<>(new ApiResponse(allCommunities, "all Communities Found  Successfully"), HttpStatus.OK);
    }

    @GetMapping("/by-user")
    public ResponseEntity<ApiResponse> getCommunitiesByUser() {
        Long currentUserId = jwtHelper.getCurrentUserId();
        List<CreateCommunitiesDTO> usersCommunity = communityService.getCommunitiesByUser(currentUserId);
        return new ResponseEntity<>(new ApiResponse(usersCommunity, "all Communities Found  Successfully"), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCommunityById(@PathVariable Long id) {
        CreateCommunitiesDTO communityById = communityService.getCommunityById(id);
        return new ResponseEntity<>(new ApiResponse(communityById, " Communities Found  Successfully"), HttpStatus.OK);
    }


    @GetMapping("name/{name}")
    public ResponseEntity<ApiResponse> getCommunityByName(@PathVariable String  name) {
        String decodedName = URLDecoder.decode(name, StandardCharsets.UTF_8);
        CreateCommunitiesDTO communityById = communityService.getCommunityByName(decodedName);
        return new ResponseEntity<>(new ApiResponse(communityById, " Communities Found  Successfully"), HttpStatus.OK);
    }

    public String uploadCommunityImage(MultipartFile file) {
        try {
            // Create a directory named "CommunityUploads" if it doesn't exist
            String folderName = "CommunityUploads";
            Path uploadDir = Paths.get(folderName);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // Generate unique file name
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadDir.resolve(fileName);

            // Save file
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return "/" + folderName + "/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Community image upload failed", e);
        }
    }



}
