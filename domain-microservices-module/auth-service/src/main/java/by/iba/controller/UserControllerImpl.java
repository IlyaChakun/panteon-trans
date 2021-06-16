package by.iba.controller;


import by.iba.dto.ApiResponse;
import by.iba.dto.UserDTO;
import by.iba.security.mail.UserSecurityMailServiceImpl;
import by.iba.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
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
        log.info("in create user ");

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

        userService.confirmUserAccount(confirmationToken);

        return ResponseEntity.ok(
                new ApiResponse(true, "Account confirmed successfully")
        );
    }


}
