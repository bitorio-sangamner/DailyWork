package com.user.service.repositories;

import com.user.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User,String>
{
    //if you want to implement any custom method or query

    User findByEmail(String email);
}
