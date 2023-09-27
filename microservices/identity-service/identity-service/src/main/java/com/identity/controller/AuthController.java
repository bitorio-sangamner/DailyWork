package com.identity.controller;

import com.identity.entity.UserIdentity;
import com.identity.model.JwtRequest;
import com.identity.model.JwtResponse;
import com.identity.repository.UserIdentityRepository;
import com.identity.service.AuthService;
import com.identity.service.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthService authService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    private UserIdentityRepository userIdentityRepository;

    @PostMapping("/register")
    public ResponseEntity<Object> addNewUser(@RequestBody UserIdentity user)
    {
        System.out.println("Hii");
        String msg=authService.saveUser(user);

        //restTemplate.getForObject("http://USER-SERVICE/")

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/Login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        System.out.println("Hello");
        JwtResponse response=new JwtResponse();
        //this.doAuthenticate(request.getEmail(), request.getPassword());

      UserIdentity user=userIdentityRepository.findByEmail(request.getEmail());

      boolean isMatch=passwordEncoder.matches(request.getPassword(),user.getPassword());

      if(isMatch) {

          UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getEmail());
          String token = authService.genarateToken(userDetails);

           response = JwtResponse.builder()
                  .jwtToken(token)
                  .username(userDetails.getUsername()).build();
          return new ResponseEntity<>(response, HttpStatus.OK);

      }

      return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

//    private void doAuthenticate(String email, String password) {
//
//        System.out.println(email);
//        System.out.println(password);
//
//        String newPassword=passwordEncoder.encode(password);
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, newPassword);
//        try {
//
//            System.out.println("inside deAuthenticate");
//            manager.authenticate(authentication);
//            System.out.println(password);
//
//
//        } catch (BadCredentialsException e) {
//            throw new BadCredentialsException(" Invalid Username or Password  !!");
//        }
//
//    }

    @GetMapping("/validate")
    public String validToken(@RequestParam("token") String token)
    {
        String msg="";
        Boolean flag=authService.validateToken(token);

        if(flag)
        {
           msg="token validate!!";
            return msg;
        }

            msg="token is not valid";


        return msg;
    }

    @GetMapping("/getUserDatails/{userName}")
    UserDetails getUserDetailsFromCustomeUserDetails(@PathVariable("userName") String userName)
    {
        UserDetails userDetails=authService.getUserDetailsFromCustomeUserDetails(userName);
         return userDetails;
    }
}
