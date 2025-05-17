package com.Mohammad.CareerXpert.Entities;

import jakarta.persistence.*;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String userName;
    private String bio;
    private String location;
    private String website;
    private String profileimgurl;
    private String bannelurl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;


    public Profile(Long id, String name, String username, String bio, String location, String website, String profileimgurl, String bannelurl, Users user) {
        this.id = id;
        this.name = name;
        this.userName = username;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.profileimgurl = profileimgurl;
        this.bannelurl = bannelurl;
        this.user = user;
    }

    public Profile() {
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
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
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
}
