package com.Mohammad.CareerXpert.DTOS;


public class RegisterUserDTO {

    private Long userID;
    private String userName;
    private String email;
    private String token;


    public RegisterUserDTO(Long userID, String userName, String email, String token) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RegisterUserDTO() {
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
