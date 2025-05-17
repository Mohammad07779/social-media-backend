package com.Mohammad.CareerXpert.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class JoinCommunities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Communities community;

    private LocalDateTime joinedAt;

    public JoinCommunities(Long id, Users user, Communities community, LocalDateTime joinedAt) {
        this.id = id;
        this.user = user;
        this.community = community;
        this.joinedAt = joinedAt;
    }

    public JoinCommunities() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Communities getCommunity() {
        return community;
    }

    public void setCommunity(Communities community) {
        this.community = community;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}
