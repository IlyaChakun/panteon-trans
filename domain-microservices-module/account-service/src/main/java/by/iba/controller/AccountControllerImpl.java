package by.iba.controller;

import by.iba.client.dto.CompanyResp;
import by.iba.common.dto.ApiResponse;
import by.iba.common.dto.PatchReq;
import by.iba.dto.AccountReq;
import by.iba.dto.AccountResp;
import by.iba.dto.CompanyReq;
import by.iba.dto.PasswordReq;
import by.iba.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;


@RestController
@AllArgsConstructor
@Slf4j
public class AccountControllerImpl implements AccountController {

    private final AccountService accountService;


    @Override
    public ResponseEntity<AccountResp> save(@Valid AccountReq accountReq) {
        AccountResp savedAccount = accountService.save(accountReq);

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
    public ResponseEntity<AccountResp> patch(Long id, PatchReq patch) {


        AccountResp updated = accountService.partialUpdate(patch, id);

        return ResponseEntity
                .ok()
                .body(updated);
    }

    @Override
    public ResponseEntity<AccountResp> findById(String accountId, Principal principal) {
        log.info("Received a request to find account by id = {} ", accountId);

        AccountResp savedAccount = accountService.findById(Long.valueOf(accountId));

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
    public ResponseEntity<ApiResponse> recoverPasswordConfirmation(String token, PasswordReq passwordReq) {

        accountService.updatePasswordFromRecovery(token, passwordReq);

        return ResponseEntity.ok(
                new ApiResponse(true, "password recovered")
        );
    }

    @Override
    public ResponseEntity<ApiResponse> updatePassword(PasswordReq passwordReq, Long userId) {
        log.info("Received a request to update password for user with id = {} ", userId);

        accountService.updatePassword(userId, passwordReq);


        return ResponseEntity.ok(
                new ApiResponse(true, "password updated")
        );
    }

    @Override
    public ResponseEntity<ApiResponse> addCompanyToAccount(Long id, Long companyId) {
        log.info("Received a request to add company with id = {} to user with id = {} ", companyId, id);

        accountService.addCompanyToAccount(id, companyId);

        return ResponseEntity.ok(
                new ApiResponse(true, "company created")
        );
    }
}
