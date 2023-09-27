package dev.rsm.repository;

import dev.rsm.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Integer> {
    Optional<UserCredentials> findByUsername(String username);

    Optional<Object> findByEmail(String username);
}
