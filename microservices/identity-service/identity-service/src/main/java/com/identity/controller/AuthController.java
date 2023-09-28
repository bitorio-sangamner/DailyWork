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
        String msg = "User added successfully!!";
        try {


            System.out.println("Hii");
            UserIdentity userObj = new UserIdentity();

            userObj.setPassword(user.getPassword());
            userObj.setName(user.getName());
            userObj.setEmail(user.getEmail());
            userObj.setAbout(user.getAbout());

            boolean result = authService.saveUser(user);

//        System.out.println(user.getEmail());
//        System.out.println(user.getPassword());
//        System.out.println(user.getAbout());

            ResponseEntity<Boolean> response = restTemplate.postForEntity(
                    "http://USER-SERVICE/signUp/addUser",
                    userObj, Boolean.class);


            return new ResponseEntity<>(msg, HttpStatus.OK);

        }//try

        catch(Exception e)
        {
            msg="Sorry,Something went wrong";
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }







    }

    @GetMapping("/Login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        System.out.println("Hello");
        JwtResponse response=new JwtResponse();
        //this.doAuthenticate(request.getEmail(), request.getPassword());

      UserIdentity user=userIdentityRepository.findByEmail(request.getEmail());

      UserIdentity userResponse = restTemplate.getForObject("http://USER-SERVICE/user/details/"+request.getEmail(),UserIdentity.class);

      System.out.println("password from user :"+userResponse.getPassword());
       System.out.println("password from request : "+request.getPassword());

      boolean isMatch=passwordEncoder.matches(request.getPassword(),user.getPassword());

      if(isMatch && userResponse!=null && userResponse.getPassword().equals(request.getPassword())) {

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

//    @GetMapping("/validate/{token}")
//    public String validToken(@PathVariable("token") String token)
//    {
//        System.out.println("inside validate method");
//        String msg="";
//        Boolean flag=authService.validateToken(token);
//
//        if(flag)
//        {
//           msg="token validate!!";
//            return msg;
//        }
//
//            msg="token is not valid";
//
//
//        return msg;
//    }

//    @GetMapping("/getUserDatails/{userName}")
//    UserDetails getUserDetailsFromCustomeUserDetails(@PathVariable("userName") String userName)
//    {
//        UserDetails userDetails=authService.getUserDetailsFromCustomeUserDetails(userName);
//         return userDetails;
//    }
}
