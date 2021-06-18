package by.iba.service;

import by.iba.common.exception.ServiceException;
import by.iba.domain.ConfirmationToken;
import by.iba.domain.User;
import by.iba.dto.UserDTO;
import by.iba.dto.mapper.UserMapperDTO;
import by.iba.repository.ConfirmationTokenRepository;
import by.iba.repository.UserRepository;
import by.iba.security.mail.UserSecurityMailService;
import by.iba.security.mail.exception.ConfirmationTokenBrokenLinkException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final UserRepository userRepository;
    private final UserMapperDTO userMapper;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserSecurityMailService userSecurityMailService;

    @Override
    public UserDTO save(UserDTO userDTO) {
        log.info("Saving user, email = {} , password = {} , firstName = {} , lastName = {} ",
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getFirstName(),
                userDTO.getLastName());

        validateEmailAvailabilityOrThrowException(userDTO.getEmail());

        User user = userMapper.toEntity(userDTO);

        encodeUserPassword(user);

        User savedUser = userRepository.save(user);

        log.info("new user with email = {} has been created", user.getEmail());

        createAndSendConfirmationToken(savedUser);

        log.info("Confirmation token send");

        return userMapper.toDto(savedUser);
    }

    private void createAndSendConfirmationToken(User savedUser) {
        ConfirmationToken confirmationToken = new ConfirmationToken(savedUser.getUserId());
        confirmationTokenRepository.save(confirmationToken);
        doSendConfirmationMail(savedUser, confirmationToken);
    }

    private void doSendConfirmationMail(User user, ConfirmationToken confirmationToken) {
        //
        userSecurityMailService.sendConfirmationEmail(user.getEmail(), confirmationToken.getConfirmationToken());
        //
    }

    @Override
    @Transactional
    public void confirmUserAccount(String confirmationToken) {
        Optional<ConfirmationToken> token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token.isPresent()) {
            User user = userRepository.findByUserId(token.get().getUserId());
            user.setEmailConfirmed(true);
            userRepository.save(user);
        } else {
            log.error("Link with token is broken");
            throw new ConfirmationTokenBrokenLinkException("Link is broken!");
        }
    }

    private void validateEmailAvailabilityOrThrowException(final String email) {
        if (userRepository.existsByEmail(email)) {
            log.error("Duplicate email");
            throw new ServiceException(HttpStatus.CONFLICT.value(), "exception.user.duplicate_email_error");
        }
    }

    private void encodeUserPassword(final User user) {
        final String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);
    }
}
