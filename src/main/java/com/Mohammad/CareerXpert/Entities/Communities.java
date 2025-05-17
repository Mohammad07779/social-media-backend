package com.Mohammad.CareerXpert.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Communities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = false, length = 300)
    private String description;

    private String logoUrl;

    @Column(nullable = false)
    private String types;    // Public or Private

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Users createdBy;

    //@Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<JoinCommunities> members = new ArrayList<>();

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<PostCommunities> posts = new ArrayList<>();


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

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<JoinCommunities> getMembers() {
        return members;
    }

    public void setMembers(List<JoinCommunities> members) {
        this.members = members;
    }

    public List<PostCommunities> getPosts() {
        return posts;
    }

    public void setPosts(List<PostCommunities> posts) {
        this.posts = posts;
    }

    public Communities() {
    }

    public Communities(Long id, String name, String description, String logoUrl, String types, Users createdBy, LocalDateTime createdAt, List<JoinCommunities> members, List<PostCommunities> posts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logoUrl = logoUrl;
        this.types = types;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.members = members;
        this.posts = posts;
    }
}