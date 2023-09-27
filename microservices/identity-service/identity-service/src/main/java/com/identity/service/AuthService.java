package com.identity.service;

import com.identity.entity.UserIdentity;
import com.identity.repository.UserIdentityRepository;
import com.identity.security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserIdentityRepository userIdentityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtHelper jwtHelper;
    public String saveUser(UserIdentity user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        String randomUserId= UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        userIdentityRepository.save(user);

        return "user added to the system";

    }

    public String genarateToken(UserDetails userDetails)
    {
        String token=jwtHelper.generateToken(userDetails);

        return token;
    }

    public Boolean  validateToken(String token)
    {
        String username=jwtHelper.getUsernameFromToken(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
       return jwtHelper.validateToken(token,userDetails);
    }

    public UserDetails getUserDetailsFromCustomeUserDetails(String userName) {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);

        return userDetails;
    }
}
