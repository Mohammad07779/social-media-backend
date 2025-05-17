package com.Mohammad.CareerXpert.DTOS;

import java.time.LocalDateTime;

public class PostCommunitiesDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private Long postedByUserId;
    private Long communityId;
    private String imageUrls;
    private String videoUrls;

    public PostCommunitiesDTO(Long id, String title, String content, LocalDateTime createdDate, Long postedByUserId, Long communityId, String imageUrls, String videoUrls) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.postedByUserId = postedByUserId;
        this.communityId = communityId;
        this.imageUrls = imageUrls;
        this.videoUrls = videoUrls;
    }

    public PostCommunitiesDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getPostedByUserId() {
        return postedByUserId;
    }

    public void setPostedByUserId(Long postedByUserId) {
        this.postedByUserId = postedByUserId;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getVideoUrls() {
        return videoUrls;
    }

    public void setVideoUrls(String videoUrls) {
        this.videoUrls = videoUrls;
    }
}
