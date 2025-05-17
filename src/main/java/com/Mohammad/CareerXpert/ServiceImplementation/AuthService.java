package com.Mohammad.CareerXpert.ServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private final CustomUSerDetailsService customUSerDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(CustomUSerDetailsService customUSerDetailsService, PasswordEncoder passwordEncoder) {
        this.customUSerDetailsService = customUSerDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public Authentication verifyUser(String email, String password) {
        UserDetails userDetails = customUSerDetailsService.loadUserByUsername(email);
        if(userDetails==null){
            throw new BadCredentialsException("Invalid UserName and Password");
        } else if (!passwordEncoder.matches(password,userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid UserName and Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
