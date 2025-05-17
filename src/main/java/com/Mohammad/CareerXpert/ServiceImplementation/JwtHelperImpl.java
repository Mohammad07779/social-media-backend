package com.Mohammad.CareerXpert.ServiceImplementation;

import com.Mohammad.CareerXpert.Entities.Users;
import com.Mohammad.CareerXpert.Exception.ResourceNotFoundException;
import com.Mohammad.CareerXpert.Repositories.UserRepository;
import com.Mohammad.CareerXpert.Services.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtHelperImpl implements JwtHelper {

    @Autowired
    private UserRepository userRepository; // Assuming you have a UserRepository to fetch user details

    @Override
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String email = ((UserDetails) principal).getUsername();
                Users user = userRepository.findByEmailIgnoreCase(email).orElseThrow(()->new ResourceNotFoundException("user Not Found With  " + email));
                if (user != null) {
                    return (user.getId());
                } else {
                    throw new IllegalStateException("User not found");
                }
            }
        }
        throw new IllegalStateException("User is not authenticated");
    }
}
