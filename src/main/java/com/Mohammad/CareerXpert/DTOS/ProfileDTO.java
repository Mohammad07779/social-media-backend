package com.Mohammad.CareerXpert.DTOS;


import java.time.LocalDateTime;

public class ProfileDTO {

    private Long id;
    private String name;
    private String username;
    private String bio;
    private String location;
    private String website;
    private String profileimgurl;
    private String bannelurl;
    private LocalDateTime joinedDate;

    public ProfileDTO(Long id, String name, String username, String bio, String location, String website, String profileimgurl, String bannelurl, LocalDateTime joinedDate) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.profileimgurl = profileimgurl;
        this.bannelurl = bannelurl;
        this.joinedDate = joinedDate;
    }

    public ProfileDTO() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getProfileimgurl() {
        return profileimgurl;
    }

    public void setProfileimgurl(String profileimgurl) {
        this.profileimgurl = profileimgurl;
    }

    public String getBannelurl() {
        return bannelurl;
    }

    public void setBannelurl(String bannelurl) {
        this.bannelurl = bannelurl;
    }

    public LocalDateTime getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDateTime joinedDate) {
        this.joinedDate = joinedDate;
    }
}
