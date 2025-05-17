package com.Mohammad.CareerXpert.DTOS;

import com.Mohammad.CareerXpert.Entities.Post;
import com.Mohammad.CareerXpert.Entities.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;


import java.time.LocalDateTime;

public class LikeDTO {

    private Long id;
    @JsonIgnore
    private Users users;
    @JsonIgnore
    private Post post;
    private LocalDateTime likedAt;


    public LikeDTO() {
    }


    public LikeDTO(Long id, Users users, Post post, LocalDateTime likedAt) {
        this.id = id;
        this.users = users;
        this.post = post;
        this.likedAt = likedAt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public LocalDateTime getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(LocalDateTime likedAt) {
        this.likedAt = likedAt;
    }
}
