package com.Mohammad.CareerXpert.ServiceImplementation;

import com.Mohammad.CareerXpert.DTOS.UserResponseDTO;
import com.Mohammad.CareerXpert.Entities.Users;
import com.Mohammad.CareerXpert.Exception.ResourceNotFoundException;
import com.Mohammad.CareerXpert.Repositories.UserRepository;
import com.Mohammad.CareerXpert.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserResponseDTO> getAllUser() {

      return userRepository.findAll()
              .stream()
              .map(user->modelMapper.map(user,UserResponseDTO.class))
              .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO findUserByEmail(String email) {
        Users user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResourceNotFoundException("user Not Found With " + email));
        return modelMapper.map(user,UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO findUserByUserName(String userName) {
        Users user = userRepository.findByUserNameIgnoreCase(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User Not With " + userName));
        return modelMapper.map(user,UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO findUserById(Long userid) {
        Users user = userRepository.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("User Not With " + userid));
        return modelMapper.map(user,UserResponseDTO.class);
    }

    @Override
    public void deleteUserAccount(Long id) {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not With " + id));
        userRepository.deleteById(id);
    }



    @Override
    public UserResponseDTO updateEmailByOldEmail(String oldEmail, String newEmail,Long userid) {
        userRepository.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User Not With " + userid));
        Users save=null;
        if(userRepository.existsByEmail(newEmail)){
            throw new KeyAlreadyExistsException("User Already Registered With  "+ newEmail );
        }
         if(userRepository.existsByEmail(oldEmail)){
             UserResponseDTO dto = findUserByEmail(oldEmail);
             Users user  = modelMapper.map(dto, Users.class);
             user.setEmail(newEmail);
              save = userRepository.save(user);
         }
        return modelMapper.map(save,UserResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> searchUserByName(String name) {
        List<Users> users = userRepository.searchUserByName(name);
        return users.stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

//    @Override
//    public Integer countAllUsers() {
//         return userRepository.countAllUsers();
//    }


}
