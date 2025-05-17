package com.Mohammad.CareerXpert.Controllers;

import com.Mohammad.CareerXpert.DTOS.LoginDTO;
import com.Mohammad.CareerXpert.DTOS.RegisterUserDTO;
import com.Mohammad.CareerXpert.DTOS.UserRequestDTO;
import com.Mohammad.CareerXpert.Services.PublicService;
import com.Mohammad.CareerXpert.Utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    private final PublicService publicService;

    @Autowired
    public PublicController(PublicService publicService) {
        this.publicService = publicService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody UserRequestDTO dto) {
        RegisterUserDTO user = publicService.RegisterUser(dto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + user.getToken());
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers)
                .body(new ApiResponse(user, "User Registered Successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> LoginUser(@RequestBody LoginDTO dto) {
        RegisterUserDTO user = publicService.manuallyLogin(dto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + user.getToken());
        return ResponseEntity.status(HttpStatus.OK).headers(headers)
                .body(new ApiResponse(user, "User Login Successfully"));

    }


}
