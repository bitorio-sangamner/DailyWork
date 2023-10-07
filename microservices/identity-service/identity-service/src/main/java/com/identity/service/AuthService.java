package com.identity.service;

import com.identity.entity.UserIdentity;
import com.identity.repository.UserIdentityRepository;
import com.identity.security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
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
//    public boolean saveUser(UserIdentity user)
//    {
//        try {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//            String randomUserId = UUID.randomUUID().toString();
//            user.setUserId(randomUserId);
//            userIdentityRepository.save(user);
//
//            return true;
//        }
//
//        catch(Exception e)
//        {
//            e.printStackTrace();
//            return false;
//        }
//    }

    public boolean saveUser(UserIdentity user)
    {
        try
        {
            System.out.println("inside saveUser");

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            String randomUserId = UUID.randomUUID().toString();
            user.setUserId(randomUserId);
            userIdentityRepository.save(user);

            return true;
        }

        catch(Exception e)
        {
            return false;
        }
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

    public UserIdentity getUserIdentityByEmail(String email)
    {
        UserIdentity user=userIdentityRepository.findByEmail(email);
        return user;
    }

    public String updatePassword(String email, String password) {

        try {
            UserIdentity user = userIdentityRepository.findByEmail(email);
            user.setPassword(password);

            userIdentityRepository.save(user);

            return "password updated successfully!!";

        }

        catch(Exception e)
        {
            e.printStackTrace();
        }

        return "something went wrong,user not found please give correct email!!";

    }

    public String verifyAccount(String email, String otp)
    {
        UserIdentity user=userIdentityRepository.findByEmail(email);

        if(user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(), LocalDateTime.now()).getSeconds()<(10*60))
        {
           user.setActive(true);

           userIdentityRepository.save(user);

           return "OTP verified you can login";
        }

        return "Please regenerate otp and try again";
    }
}
