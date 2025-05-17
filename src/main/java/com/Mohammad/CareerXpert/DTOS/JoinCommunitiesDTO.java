package com.Mohammad.CareerXpert.DTOS;

import java.time.LocalDateTime;

public class JoinCommunitiesDTO {

    private Long id;
    private Long userId;
    private Long communityId;
    private LocalDateTime joinedDate;
    private String name;
    private String logourl;


    public JoinCommunitiesDTO(Long id, Long userId, Long communityId, LocalDateTime joinedDate, String name, String logourl) {
        this.id = id;
        this.userId = userId;
        this.communityId = communityId;
        this.joinedDate = joinedDate;
        this.name = name;
        this.logourl = logourl;
    }

    public JoinCommunitiesDTO() {

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

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public LocalDateTime getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDateTime joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }
}
