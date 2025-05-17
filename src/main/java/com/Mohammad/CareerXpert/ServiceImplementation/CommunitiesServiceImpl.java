package com.Mohammad.CareerXpert.ServiceImplementation;

import com.Mohammad.CareerXpert.DTOS.CreateCommunitiesDTO;
import com.Mohammad.CareerXpert.Entities.Communities;
import com.Mohammad.CareerXpert.Entities.Users;
import com.Mohammad.CareerXpert.Exception.ResourceNotFoundException;
import com.Mohammad.CareerXpert.Repositories.CommunitiesRepository;
import com.Mohammad.CareerXpert.Repositories.UserRepository;
import com.Mohammad.CareerXpert.Services.CommunityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunitiesServiceImpl implements CommunityService {

    private final CommunitiesRepository communitiesRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public CommunitiesServiceImpl(CommunitiesRepository communitiesRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.communitiesRepository = communitiesRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public CreateCommunitiesDTO createCommunity(CreateCommunitiesDTO dto, Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Id " + userId));

        Communities community = modelMapper.map(dto, Communities.class);

        if (dto.getTypes() == null || dto.getTypes().isEmpty()) {
            community.setTypes("public");
        } else {
            community.setTypes(dto.getTypes());
        }


        community.setCreatedAt(LocalDateTime.now());
        community.setCreatedBy(user);

        Communities savedCommunity = communitiesRepository.save(community);

        return modelMapper.map(savedCommunity, CreateCommunitiesDTO.class);
    }

    @Override
    public List<CreateCommunitiesDTO> getAllCommunities() {
        return communitiesRepository.findAll().stream()
                .map(c -> {
                    CreateCommunitiesDTO dto = modelMapper.map(c, CreateCommunitiesDTO.class);
                    dto.setUserName(c.getCreatedBy().getUserName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CreateCommunitiesDTO> getCommunitiesByUser(Long userId) {
        return communitiesRepository.findByCreatedById(userId).stream()
                .map(c -> {
                    CreateCommunitiesDTO dto = modelMapper.map(c, CreateCommunitiesDTO.class);
                    dto.setUserName(c.getCreatedBy().getUserName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CreateCommunitiesDTO getCommunityById(Long id) {
        Communities community = communitiesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Community Id" + id));
        CreateCommunitiesDTO foundCommunity = modelMapper.map(community, CreateCommunitiesDTO.class);
        foundCommunity.setUserName(community.getCreatedBy().getUserName());
        return foundCommunity;
    }



    @Override
    public CreateCommunitiesDTO getCommunityByName(String name) {
        // Normalize the name: replace hyphens with spaces and trim
        String normalizedName = name.replace("-", " ").trim();

        Communities community = communitiesRepository.findByNameIgnoreCase(normalizedName)
                .orElseThrow(() -> new ResourceNotFoundException("Community with name " + name + " not found"));

        CreateCommunitiesDTO foundCommunity = modelMapper.map(community, CreateCommunitiesDTO.class);
        foundCommunity.setUserName(community.getCreatedBy().getUserName());

        return foundCommunity;
    }


}
