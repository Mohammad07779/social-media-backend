package com.Mohammad.CareerXpert.Controllers;

import com.Mohammad.CareerXpert.DTOS.UserResponseDTO;
import com.Mohammad.CareerXpert.Services.JwtHelper;
import com.Mohammad.CareerXpert.Services.UserService;
import com.Mohammad.CareerXpert.Utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtHelper jwtHelper;

    @Autowired
    public UserController(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }


    @GetMapping
    public ResponseEntity<ApiResponse> getAllUser() {
        List<UserResponseDTO> user = userService.getAllUser();
        return new ResponseEntity<>(new ApiResponse(user, "All user Retried Successfully"), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse> getUserByEmail(@PathVariable String email) {
        UserResponseDTO user = userService.findUserByEmail(email);
        return new ResponseEntity<>(new ApiResponse(user, " user Found Successfully"), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse> getUserByUserName(@PathVariable String username) {
        UserResponseDTO user = userService.findUserByUserName(username);
        return new ResponseEntity<>(new ApiResponse(user, " user Retried Successfully"), HttpStatus.OK);
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long userId) {
        UserResponseDTO user = userService.findUserById(userId);
        return new ResponseEntity<>(new ApiResponse(user, " user Retried Successfully"), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteUser() {
        Long currentUserId = jwtHelper.getCurrentUserId();
        userService.deleteUserAccount(currentUserId);
        return new ResponseEntity<>(new ApiResponse(null, "User Deleted Successfully"), HttpStatus.OK);
    }




    @PutMapping("email/{oldEmail}/{newEmail}")
    public ResponseEntity<ApiResponse> updateUserEmail(@PathVariable String oldEmail, @PathVariable String newEmail) {
        Long currentUserId = jwtHelper.getCurrentUserId();
        UserResponseDTO user = userService.updateEmailByOldEmail(oldEmail, newEmail,currentUserId);
        return new ResponseEntity<>(new ApiResponse(user, "User Updated SuccessFully"), HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<ApiResponse> searchUserByName(@PathVariable String name) {
        List<UserResponseDTO> user = userService.searchUserByName(name);
        return new ResponseEntity<>(new ApiResponse(user, "User Found Successfully"), HttpStatus.OK);
    }

//    @GetMapping("/countalluser")
//    public ResponseEntity<ApiResponse> countAllUsers() {
//        Integer count = userService.countAllUsers();
//        return new ResponseEntity<>(new ApiResponse(count, "Total Users Count Successfully"), HttpStatus.OK);
//    }


}
