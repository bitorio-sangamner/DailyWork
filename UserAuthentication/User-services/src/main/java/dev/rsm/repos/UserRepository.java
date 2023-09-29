package dev.rsm.repos;

import dev.rsm.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByUsernameAndPasswordAndEmail(String username, String password, String email);

    User getUserById(int id);

    User getUserByUsername(String username);

    User findByEmail(String email);

    User findByResetPasswordTokenAndEmail(String resetPasswordToken, String email);
}
