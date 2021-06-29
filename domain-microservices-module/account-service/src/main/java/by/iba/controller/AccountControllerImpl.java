package by.iba.controller;

import by.iba.common.dto.ApiResponse;
import by.iba.dto.AccountDTO;
import by.iba.dto.PasswordReqDTO;
import by.iba.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@AllArgsConstructor
@Slf4j
public class AccountControllerImpl implements AccountController {

    private final AccountService accountService;


    @Override
    public ResponseEntity<AccountDTO> save(@Valid AccountDTO accountDTO) {
        log.info("Received a request to save new account with id = {} ", accountDTO.getAccountId());
        AccountDTO savedAccount = accountService.save(accountDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/accounts/{id}")
                .buildAndExpand(savedAccount.getAccountId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(savedAccount);
    }

    @Override
    public ResponseEntity<AccountDTO> findById(String accountId) {
        log.info("Received a request to find account by id = {} ", accountId);

        AccountDTO savedAccount = accountService.findById(Long.valueOf(accountId));

        return ResponseEntity
                .ok()
                .body(savedAccount);
    }

    @Override
    public ResponseEntity<ApiResponse> confirmAccount(String token) {
        log.info("Received a request to confirm account using token: {}", token);

        accountService.confirmAccount(token);
        return ResponseEntity.ok(
                new ApiResponse(true, "Account confirmed successfully")
        );
    }

    @Override
    public ResponseEntity<ApiResponse> sendRecoverPasswordEmail(String userEmail) {
        log.info("Received a request to recover password");

        accountService.recoverPassword(userEmail);

        return ResponseEntity.ok(
                new ApiResponse(true, "message for recover sent")
        );

    }

    @Override
    public ResponseEntity<ApiResponse> recoverPasswordConfirmation(String token, PasswordReqDTO passwordReqDTO) {

        accountService.updatePasswordFromRecovery(token, passwordReqDTO);

        return ResponseEntity.ok(
                new ApiResponse(true, "password recovered")
        );
    }

    @Override
    public ResponseEntity<ApiResponse> updatePassword(PasswordReqDTO passwordReqDTO, Long userId) {
        log.info("Received a request to update password for user with id = {} ", userId);

        accountService.updatePassword(userId, passwordReqDTO);


        return ResponseEntity.ok(
                new ApiResponse(true, "password updated")
        );
    }
}
