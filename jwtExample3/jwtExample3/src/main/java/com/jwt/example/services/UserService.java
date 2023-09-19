package com.jwt.example.services;

import com.jwt.example.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private List<User> store=new ArrayList<>();

    public UserService()
    {
        store.add(new User(UUID.randomUUID().toString(),"Durgesh Tiwari","durgesh@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(),"Harsh Tiwari","harsh@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(),"Ankit Tiwari","ankit@gmail.com"));
    }

    public List<User> getUsers()
    {
        return this.store;
    }
}
