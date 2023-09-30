package dev.rsm.repos;

import dev.rsm.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findUserById(int id);

    Optional<User> findUserByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByResetPasswordTokenAndEmail(String resetPasswordToken, String email);

    Optional<User> findByUsernameAndPasswordAndEmail(String username, String password, String email);
}
