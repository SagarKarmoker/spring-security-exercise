package com.sagar.springSecurityExample.service;

import com.sagar.springSecurityExample.model.Users;
import com.sagar.springSecurityExample.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // place here or at security config
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10); // round = 10

    public Users register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }

    public String verify(Users user) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        return "fail";
    }
}
