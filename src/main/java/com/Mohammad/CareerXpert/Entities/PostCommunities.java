package com.Mohammad.CareerXpert.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PostCommunities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users postedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    private Communities community;

    @Column(name = "image_url")
    private String imageUrls;

    @Column(name = "video_url")
    private String videoUrls;

    public PostCommunities(Long id, String title, String content, LocalDateTime createdDate, Users postedBy, Communities community, String imageUrls, String videoUrls) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.postedBy = postedBy;
        this.community = community;
        this.imageUrls = imageUrls;
        this.videoUrls = videoUrls;
    }

    public PostCommunities() {

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

    public Users getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(Users postedBy) {
        this.postedBy = postedBy;
    }

    public Communities getCommunity() {
        return community;
    }

    public void setCommunity(Communities community) {
        this.community = community;
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

