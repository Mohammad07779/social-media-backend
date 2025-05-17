package com.Mohammad.CareerXpert.DTOS;


import com.Mohammad.CareerXpert.Entities.Users;

import java.time.LocalDateTime;

public class PostDTO {
    private Long postId;
    private String title;
    private String content;
    private String caption;
    private String imageUrl;
    private String videoUrl;
    private String hashTags;
    private Users users;
    private LocalDateTime createdAt;
    private String profileimgUrl;


    public PostDTO(Long postId, String title, String content, String caption, String imageUrl, String videoUrl, String hashTags, Users users, LocalDateTime createdAt, String profileimgUrl) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.hashTags = hashTags;
        this.users = users;
        this.createdAt = createdAt;
        this.profileimgUrl = profileimgUrl;
    }

    public PostDTO() {
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }



    public String getHashTags() {
        return hashTags;
    }

    public void setHashTags(String hashTags) {
        this.hashTags = hashTags;
    }

    public Users getUsers() {
        return users;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getProfileimgUrl() {
        return profileimgUrl;
    }

    public void setProfileimgUrl(String profileimgUrl) {
        this.profileimgUrl = profileimgUrl;
    }
}
