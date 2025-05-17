package com.Mohammad.CareerXpert.DTOS;


import java.time.LocalDateTime;


public class UserResponseDTO {

    private Long id;
    private String userName;

    private String email;

   private LocalDateTime registerAt;
   private String fullName;

    public UserResponseDTO() {

    }

    public UserResponseDTO(Long id, String userName, String email, LocalDateTime registerAt, String fullName) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.registerAt = registerAt;
        this.fullName = fullName;
    }

    public LocalDateTime getRegisterAt() {
        return registerAt;
    }

    public void setRegisterAt(LocalDateTime registerAt) {
        this.registerAt = registerAt;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
