package by.iba.service;

import by.iba.domain.User;
import by.iba.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final UserRepository repository;


    @Override
    public void create(User user) {

        Optional<User> existing = repository.findById(user.getUsername());
        existing.ifPresent(it-> {throw new IllegalArgumentException("user already exists: " + it.getUsername());});

        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);

        repository.save(user);

        log.info("new user has been created: {}", user.getUsername());
    }
}
