package by.iba.controller;

import by.iba.common.dto.ApiResponse;
import by.iba.common.dto.PatchReq;
import by.iba.dto.AccountReq;
import by.iba.dto.AccountResp;
import by.iba.dto.PasswordReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/accounts")
@CrossOrigin(origins = "*")
public interface AccountController {

    @PostMapping
    ResponseEntity<AccountResp> save(@Valid @RequestBody AccountReq accountReq);

    @PatchMapping("/{id}")
    ResponseEntity<AccountResp> patch(@PathVariable("id") Long accountId, @Valid @RequestBody PatchReq patchReq);

    @GetMapping("/{id}")
    ResponseEntity<AccountResp> findById(@PathVariable("id") String accountId, Principal principal);

    @GetMapping("/confirm/{token}")
    ResponseEntity<ApiResponse> confirmAccount(@PathVariable("token") String token);

    @GetMapping("/password/recover/{email}")
    ResponseEntity<ApiResponse> sendRecoverPasswordEmail(@PathVariable("email") String userEmail);

    @PostMapping(value = "/password/recover/{token}")
    ResponseEntity<ApiResponse> recoverPasswordConfirmation(@PathVariable("token") String token,
                                                            @RequestBody @Valid PasswordReq passwordReq);

    @PutMapping("/password/update/{userId}")
    ResponseEntity<ApiResponse> updatePassword(@RequestBody @Valid PasswordReq passwordReq,
                                                  @PathVariable Long userId);

}
