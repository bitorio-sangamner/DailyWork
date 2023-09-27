package com.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.identity.entity.UserIdentity;

public interface UserIdentityRepository extends JpaRepository<UserIdentity,Integer>
{
    UserIdentity findByEmail(String email);
}
