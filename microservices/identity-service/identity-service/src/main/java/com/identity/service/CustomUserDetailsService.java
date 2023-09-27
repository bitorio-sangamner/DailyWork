package com.identity.service;


import com.identity.entity.UserIdentity;
import com.identity.repository.UserIdentityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserIdentityRepository userIdentityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        UserIdentity user = userIdentityRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder().
                username(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                        .build();


        return userDetails;
    }



}
