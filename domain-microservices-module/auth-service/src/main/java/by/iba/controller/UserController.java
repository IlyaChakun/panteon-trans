package by.iba.controller;

import by.iba.common.dto.ApiResponse;
import by.iba.dto.PasswordDTO;
import by.iba.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


@RequestMapping("/users")
@CrossOrigin(origins = "*")
public interface UserController {

    @GetMapping("/current")
    Principal getUser(Principal principal);

    @PreAuthorize("#oauth2.hasScope('server')")
    @PostMapping()
    ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO user);

    @PreAuthorize("#oauth2.hasScope('server')")
    @PostMapping(value = "/confirm-account/{token}")
    ResponseEntity<ApiResponse> confirmUserAccount(@PathVariable("token") String confirmationToken);

    @PreAuthorize("#oauth2.hasScope('server')")
    @GetMapping("/password/recover/{email}")
    ResponseEntity<ApiResponse> recoverPassword(@PathVariable("email") String userEmail);

    @PreAuthorize("#oauth2.hasScope('server')")
    @PostMapping(value = "/password/recover/{token}")
    ResponseEntity<PasswordDTO> recoverPasswordConfirmation(@PathVariable("token") String token,
                                                            @RequestBody @Valid PasswordDTO passwordDTO);

    @PreAuthorize("#oauth2.hasScope('server')")
    @PutMapping(value = "/password/update/{userId}")
    ResponseEntity<ApiResponse> updatePassword(@RequestBody @Valid PasswordDTO passwordDTO,
                                               @PathVariable Long userId);

}
