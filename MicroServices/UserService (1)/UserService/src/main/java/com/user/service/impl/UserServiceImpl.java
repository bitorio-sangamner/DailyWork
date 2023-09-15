package com.user.service.impl;

import com.user.service.entity.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.repositories.UserRepository;
import com.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {

        //generate unique userId
        String randomUserId=UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {

        return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server!! :"+userId));
    }
}
