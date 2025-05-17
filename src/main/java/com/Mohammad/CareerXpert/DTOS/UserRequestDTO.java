package com.Mohammad.CareerXpert.DTOS;



import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class UserRequestDTO {
    private String fullName;
    private String userName;
    private String email;
    private long mobileNumber;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    private String password;


    public UserRequestDTO(String fullName, String userName, String email, long mobileNumber, LocalDate birthDate, String password) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.birthDate = birthDate;
        this.password = password;
    }

    public UserRequestDTO() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
