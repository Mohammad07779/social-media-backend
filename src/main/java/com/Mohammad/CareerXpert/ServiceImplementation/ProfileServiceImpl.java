package com.Mohammad.CareerXpert.ServiceImplementation;

import com.Mohammad.CareerXpert.DTOS.ProfileDTO;
import com.Mohammad.CareerXpert.Entities.Profile;
import com.Mohammad.CareerXpert.Entities.Users;
import com.Mohammad.CareerXpert.Exception.ResourceNotFoundException;
import com.Mohammad.CareerXpert.Repositories.ProfileRepository;
import com.Mohammad.CareerXpert.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.nio.file.Paths;

import java.util.UUID;


@Service
public class ProfileServiceImpl  {

    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }



    public ProfileDTO updateUserDetails(ProfileDTO dto, String userName, MultipartFile profileImage, MultipartFile bannerImage) {
        // Fetch user from Users table
        Users user = userRepository.findByUserNameIgnoreCase(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userName));

        // Fetch profile by userName
        Profile profile = profileRepository.findByUserName(userName)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found for user: " + userName));

        // Update profile fields
        profile.setUsername(dto.getUsername());
        profile.setName(dto.getName());
        profile.setBio(dto.getBio());
        profile.setLocation(dto.getLocation());
        profile.setWebsite(dto.getWebsite());

        // Save profile image if provided
        if (profileImage != null && !profileImage.isEmpty()) {
            String profileImageUrl = saveFile(profileImage, "profile");
            profile.setProfileimgurl(profileImageUrl);
        }

        // Save banner image if provided
        if (bannerImage != null && !bannerImage.isEmpty()) {
            String bannerImageUrl = saveFile(bannerImage, "profile");
            profile.setBannelurl(bannerImageUrl);
        }

        Profile updatedProfile = profileRepository.save(profile);

        // Update user-related fields if needed
        updateUser(userName, dto);

        // Map to DTO
        ProfileDTO map = modelMapper.map(updatedProfile, ProfileDTO.class);
        map.setJoinedDate(user.getRegisterAT());

        return map;
    }



    public ProfileDTO getProfile(String username) {
        Profile profile = profileRepository.findByUserName(username).orElseThrow(() -> new ResourceNotFoundException("User not found With" + username));

        Users users = userRepository.findByUserNameIgnoreCase(username).orElseThrow(() -> new ResourceNotFoundException("User not found With" + username));

        ProfileDTO map = modelMapper.map(profile, ProfileDTO.class);
        map.setJoinedDate(users.getRegisterAT());

        return map;
    }

    public void updateUser(String username, ProfileDTO dto) {
        Users user = userRepository.findByUserNameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        user.setUserName(dto.getUsername());
        user.setFullName(dto.getName());
        userRepository.save(user);

    }

    private String saveFile(MultipartFile file, String folderName) {
        try {
            // Use absolute path for better reliability
            String uploadDir = "uploads/" + folderName + "/";
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();

            // Create directories if they don't exist
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate unique filename
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            // Save file
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Return consistent URL path
            return "/" + uploadDir + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }


}
