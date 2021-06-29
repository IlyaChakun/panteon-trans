package by.iba.controller;

import by.iba.common.dto.ApiResponse;
import by.iba.dto.AccountDTO;
import by.iba.dto.PasswordReqDTO;
import by.iba.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/accounts")
@CrossOrigin(origins = "*")
public interface AccountController {

    @PostMapping
    ResponseEntity<AccountDTO> save(@Valid @RequestBody AccountDTO accountDTO);

    @GetMapping("/{id}")
    ResponseEntity<AccountDTO> findById(@PathVariable("id") String accountId);

    @GetMapping("/confirm/{token}")
    ResponseEntity<ApiResponse> confirmAccount(@PathVariable("token") String token);

    @GetMapping("/password/recover/{email}")
    ResponseEntity<ApiResponse> sendRecoverPasswordEmail(@PathVariable("email")String userEmail);

    @PostMapping(value = "/password/recover/{token}")
    ResponseEntity<ApiResponse> recoverPasswordConfirmation(@PathVariable("token") String token,
                                                            @RequestBody @Valid PasswordReqDTO passwordReqDTO);

    @GetMapping("/password-recover")
    ResponseEntity<PasswordReqDTO> updatePassword(@RequestBody PasswordReqDTO passwordReqDTO);


}
