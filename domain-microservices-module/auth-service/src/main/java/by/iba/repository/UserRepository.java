package by.iba.repository;

import by.iba.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByEmail(String email);

    User findByUserId(Long userId);

    Boolean existsByEmail(String email);

   User findUserByUserName(String userName);

}
