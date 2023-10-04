package com.user.service.services;

import com.user.service.entity.User;

import java.util.List;

public interface UserService {

    //user opeartions

    //create user
    User saveUser(User user);

    //GET all users
    List<User> getAllUser();

    //get single user of given userId

    User getUser(String userId);

    User findUserByEmail(String email);

    String setPassword(String email, String newPassword);

    User updateUser(User userObj);

    User getByResetPasswordToken(String token);

    String updatePassword (User user,String password);


    //TODO: delete
    //TODO: update
}
