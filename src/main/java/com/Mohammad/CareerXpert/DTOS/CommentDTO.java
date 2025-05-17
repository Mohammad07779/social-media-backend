package com.Mohammad.CareerXpert.DTOS;

import com.Mohammad.CareerXpert.Entities.Users;

import java.time.LocalDateTime;

public class CommentDTO {

    private Long id;
    private Long userId;
    private String userName;
    private Long postId;
    private String content;
    private LocalDateTime commentedAt;
    private String profileImg;

    public CommentDTO(Long id, Long userId, String userName, Long postId, String content, LocalDateTime commentedAt, String profileImg) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.postId = postId;
        this.content = content;
        this.commentedAt = commentedAt;
        this.profileImg = profileImg;
    }

    public CommentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCommentedAt() {
        return commentedAt;
    }

    public void setCommentedAt(LocalDateTime commentedAt) {
        this.commentedAt = commentedAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
}
