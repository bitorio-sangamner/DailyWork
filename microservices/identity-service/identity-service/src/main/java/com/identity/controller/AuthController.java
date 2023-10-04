package com.identity.controller;

import com.identity.entity.UserIdentity;
import com.identity.model.JwtRequest;
import com.identity.model.JwtResponse;
import com.identity.repository.UserIdentityRepository;
import com.identity.service.AuthService;
import com.identity.service.CustomUserDetailsService;
import com.identity.util.OtpUtil;
import jakarta.validation.Valid;
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

import java.time.LocalDateTime;

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

    @Autowired
    private OtpUtil otpUtil;

//    @PostMapping("/register")
//    public ResponseEntity<Object> addNewUser(@Valid @RequestBody UserIdentity user)
//    {
//        String msg = "User added successfully!!";
//        try {
//
//
//            System.out.println("Hii");
//            UserIdentity userObj = new UserIdentity();
//
//            userObj.setPassword(user.getPassword());
//            userObj.setName(user.getName());
//            userObj.setEmail(user.getEmail());
//            userObj.setAbout(user.getAbout());
//
//            boolean result = authService.saveUser(user);
//
////        System.out.println(user.getEmail());
////        System.out.println(user.getPassword());
////        System.out.println(user.getAbout());
//
//            ResponseEntity<Boolean> response = restTemplate.postForEntity(
//                    "http://USER-SERVICE/signUp/addUser",
//                    userObj, Boolean.class);
//
//
//            return new ResponseEntity<>(msg, HttpStatus.OK);
//
//        }//try
//
//        catch(Exception e)
//        {
//            msg="Sorry,Something went wrong";
//            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }

    @PostMapping("/register")
    public ResponseEntity<Object> addNewUser(@Valid @RequestBody UserIdentity user)
    {
        String msg = "User added successfully!! Verify your account";
        try
        {
            String otp= otpUtil.generateOtp();

             user.setOtp(otp);
             user.setOtpGeneratedTime(LocalDateTime.now());
            //  restTemplate.postForEntity("http://EMAIL_SERVICE/otp/sendOtpEmail/"+user.getEmail()+"/"+otp,Boolean.class);

            System.out.println("otp :"+otp);
            System.out.println("send otp");
            System.out.println("Email :"+user.getEmail());

            //Boolean res= restTemplate.getForObject("http://EMAIL_SERVICE/otp/sendOtpEmail/" + user.getEmail() + "/" + otp, Boolean.class);
            Boolean res= restTemplate.getForObject("http://EMAIL-SERVICE/otp/sendOtpEmail/" + user.getEmail() + "/" + otp, Boolean.class);

           System.out.println("result :"+res);
            System.out.println("inside register");
            UserIdentity userObj = new UserIdentity();

            userObj.setPassword(user.getPassword());
            userObj.setName(user.getName());
            userObj.setEmail(user.getEmail());
            userObj.setAbout(user.getAbout());

            boolean result = authService.saveUser(user);

            ResponseEntity<Boolean> response = restTemplate.postForEntity(
                    "http://USER-SERVICE/signUp/addUser",
                    userObj, Boolean.class);

            return new ResponseEntity<>(msg, HttpStatus.OK);
        }

        catch(Exception e)
        {
            e.printStackTrace();
            msg="Sorry,Something went wrong";
           return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/Login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        System.out.println("Hello");
        JwtResponse response=new JwtResponse();


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
                  .username(userDetails.getUsername()).message("Login Successfully !!").build();


          return new ResponseEntity<>(response, HttpStatus.OK);

      }

      String message="incorrect email or password!!";
      response.setMessage(message);

      return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

    }



    @GetMapping("/findUserIdentityByEmail/{email}")
    public UserIdentity findUserIdentityByEmail(@PathVariable("email") String email)
    {
        UserIdentity user=new UserIdentity();

        try
        {
             user=authService.getUserIdentityByEmail(email);

           return user;
        }

        catch(Exception e)
        {
          e.printStackTrace();
          return user;
        }
    }

    @PutMapping("/updatePassword/{email}/{password}")
    public String updatePassword(@PathVariable("email") String email,@PathVariable("password") String password)
    {
        String msg=authService.updatePassword(email,password);

         return msg;
    }

    @PutMapping("/verify-account")
    public ResponseEntity<String> verifyAccount(@RequestParam String email,@RequestParam String otp)
    {
        return new ResponseEntity<>(authService.verifyAccount(email,otp),HttpStatus.OK);
    }
}
