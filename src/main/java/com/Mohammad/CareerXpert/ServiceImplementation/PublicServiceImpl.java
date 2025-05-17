package com.Mohammad.CareerXpert.ServiceImplementation;

import com.Mohammad.CareerXpert.DTOS.LoginDTO;
import com.Mohammad.CareerXpert.DTOS.RegisterUserDTO;
import com.Mohammad.CareerXpert.DTOS.UserRequestDTO;
import com.Mohammad.CareerXpert.Entities.Profile;
import com.Mohammad.CareerXpert.Entities.Users;
import com.Mohammad.CareerXpert.Repositories.ProfileRepository;
import com.Mohammad.CareerXpert.Repositories.UserRepository;
import com.Mohammad.CareerXpert.Services.PublicService;
import com.Mohammad.CareerXpert.Utility.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.management.openmbean.KeyAlreadyExistsException;
import java.time.LocalDateTime;


@Service
public class PublicServiceImpl implements PublicService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthService authService;
    private final ProfileRepository profileRepository;


    @Autowired
    public PublicServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthService authService, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authService = authService;
        this.profileRepository = profileRepository;
    }

    @Override
    public RegisterUserDTO RegisterUser(UserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new KeyAlreadyExistsException("Email already exists With  "+dto.getEmail()+"   Try another.");
        }
        if (userRepository.existsByUserName(dto.getUserName())) {
            throw new KeyAlreadyExistsException("Username already taken   "+dto.getUserName()+"   Try another. ");
        }
        Users user = modelMapper.map(dto, Users.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRegisterAT(LocalDateTime.now());
        userRepository.save(user);

        Profile profile=new Profile();
        profile.setUsername(user.getUserName());
        profile.setUser(user);
        profileRepository.save(profile);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), dto.getPassword());
        String token = jwtUtil.generateToken(authentication);

        return new RegisterUserDTO(user.getId(), user.getUserName(), user.getEmail(),token);
    }

    @Override
    public RegisterUserDTO manuallyLogin(LoginDTO dto) {
        Authentication auth = authService.verifyUser(dto.getEmail(), dto.getPassword());
        Users users = userRepository.findByEmailIgnoreCase(dto.getEmail()).orElseThrow(() -> new BadCredentialsException("Invalid UserName and Password"));

        String token = jwtUtil.generateToken(auth);
        return new RegisterUserDTO(users.getId(), users.getUserName(), users.getEmail(), token);
    }
}



