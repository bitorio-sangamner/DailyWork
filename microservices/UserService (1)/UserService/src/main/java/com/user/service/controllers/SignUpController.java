package com.user.service.controllers;

import com.user.service.entity.User;
import com.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signUp")
public class SignUpController {

    @Autowired
    private UserService userService;

    private Logger logger= LoggerFactory.getLogger(UserController.class);
    //@PostMapping()
//    public ResponseEntity<Object> signUp(@RequestBody User userObj)
//    {
//        User userObjResult=new User();
//        try {
//             userObjResult = userService.saveUser(userObj);
//
//        }
//
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        return  ResponseEntity.status(HttpStatus.OK).body(userObjResult);
//    }

    @PostMapping("/addUser")
    public boolean register(@RequestBody User userObj)
    {

        logger.info("inside save user controller");


        try {
             User userObjResult = userService.saveUser(userObj);
            return true;
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }
}
