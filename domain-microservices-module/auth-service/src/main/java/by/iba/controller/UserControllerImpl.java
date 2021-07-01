package by.iba.controller;


import by.iba.common.dto.ApiResponse;
import by.iba.dto.PasswordDTO;
import by.iba.dto.UserDTO;
import by.iba.security.mail.UserSecurityMailServiceImpl;
import by.iba.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;

@RestController
@Slf4j
@AllArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final UserSecurityMailServiceImpl userSecurityMailService;

    @Override
    public Principal getUser(Principal principal) {
        return principal;
    }

    @Override
    public ResponseEntity<UserDTO> save(@Valid UserDTO user) {
        log.info("Received a request to save user with id = {}", user.getUserId());

        UserDTO savedUser = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/users/{id}")
                .buildAndExpand(savedUser.getUserId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(savedUser);
    }

    @Override
    public ResponseEntity<ApiResponse> confirmUserAccount(String confirmationToken) {
        log.info("Received a request to confirm user account with token = {}", confirmationToken);

        userService.confirmUserAccount(confirmationToken);

        return ResponseEntity.ok(
                new ApiResponse(true, "Account confirmed successfully")
        );
    }

    @Override
    public ResponseEntity<ApiResponse> recoverPassword(String userEmail) {
        userService.sendRecoverMessage(userEmail);

        return ResponseEntity.ok(
                new ApiResponse(true, "Recover message sent successfully")
        );
    }

    @Override
    public ResponseEntity<PasswordDTO> recoverPasswordConfirmation(String token, PasswordDTO passwordDTO) {
        log.info("Recovery with token = {} started", token);

        userService.recoverPasswordWithToken(token, passwordDTO);

        return ResponseEntity
                .ok()
                .body(passwordDTO);
    }

    @Override
    public ResponseEntity<ApiResponse> updatePassword(PasswordDTO passwordDTO, Long userId) {
        log.info("Received a request to update password for user with id = {}", userId);

        userService.updatePassword(userId,passwordDTO);

        return ResponseEntity.ok(
                new ApiResponse(true, "Recover message updated successfully")
        );
    }

}