package by.iba.service;

import by.iba.domain.User;
import by.iba.dto.UserDTO;
import by.iba.dto.mapper.UserMapperDTO;
import by.iba.exception.ServiceException;
import by.iba.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final UserRepository userRepository;
    private final UserMapperDTO userMapper;

    @Override
    public UserDTO save(UserDTO userDTO) {
        log.info("in User service create user method");
        log.info("email={},password={},fistName={},lastName={}", userDTO.getEmail(), userDTO.getPassword(), userDTO.getFistName(), userDTO.getLastName());

        validateEmailAvailabilityOrThrowException(userDTO.getEmail());

        User user = userMapper.toEntity(userDTO);

        encodeUserPassword(user);

        User savedUser = userRepository.save(user);

        log.info("new user has been created: {}", user.getEmail());

        return userMapper.toDto(savedUser);
    }

    private void validateEmailAvailabilityOrThrowException(final String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ServiceException(HttpStatus.CONFLICT.value(), "duplicate_email_error", "User already exists: " + email);
        }
    }

    private void encodeUserPassword(final User user) {
        final String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);
    }
}
