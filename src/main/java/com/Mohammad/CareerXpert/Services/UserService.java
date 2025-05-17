package com.Mohammad.CareerXpert.Services;

import com.Mohammad.CareerXpert.DTOS.UserResponseDTO;

import java.util.List;

public interface UserService {


    List<UserResponseDTO> getAllUser();

    UserResponseDTO findUserByEmail(String email);

    UserResponseDTO findUserByUserName(String userName);

    UserResponseDTO findUserById(Long userid);

    void deleteUserAccount(Long id);


    UserResponseDTO updateEmailByOldEmail(String oldEmail, String newEmail,Long userId);

    List<UserResponseDTO> searchUserByName(String name);

//    public Integer countAllUsers();
}
