package com.Mohammad.CareerXpert.DTOS;

import java.time.LocalDateTime;
import java.util.List;

public class CreateCommunitiesDTO {
    private Long id;
    private String name;
    private String description;
    private String logoUrl;
    private String types;
    private LocalDateTime createdDate;
    private String userName;

    public CreateCommunitiesDTO(Long id, String name, String description, String logoUrl, String types, LocalDateTime createdDate, String userName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logoUrl = logoUrl;
        this.types = types;
        this.createdDate = createdDate;
        this.userName = userName;
    }

    public CreateCommunitiesDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }


    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}
