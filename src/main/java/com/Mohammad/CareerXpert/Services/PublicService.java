package com.Mohammad.CareerXpert.Services;


import com.Mohammad.CareerXpert.DTOS.LoginDTO;
import com.Mohammad.CareerXpert.DTOS.RegisterUserDTO;
import com.Mohammad.CareerXpert.DTOS.UserRequestDTO;


public interface PublicService {

    RegisterUserDTO RegisterUser(UserRequestDTO dto);
    RegisterUserDTO manuallyLogin(LoginDTO dto);


}
