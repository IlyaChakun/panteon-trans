package by.iba.client;

import by.iba.client.dto.UserResp;
import by.iba.common.dto.ApiResponse;
import by.iba.common.exception.ServiceException;
import by.iba.dto.PasswordReq;
import by.iba.dto.UserReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "auth-service")
public interface AuthServiceClient {

    @PostMapping("/users")
    ResponseEntity<UserResp> save(UserReq user) throws ServiceException;

    @PostMapping(value = "/users/confirm-account/{token}")
    ResponseEntity<ApiResponse> confirmUserAccount(@PathVariable("token") String token);

    @GetMapping("/users/password/recover/{email}")
    ResponseEntity<ApiResponse> recoverPassword(@PathVariable("email") String userEmail);

    @PostMapping(value = "/users/password/recover/{token}")
    ResponseEntity<PasswordReq> recoverPasswordConfirmation(@PathVariable("token") String token,
                                                            @RequestBody @Valid PasswordReq passwordReq);

    @PutMapping(value = "/users/password/update/{userId}")
    ResponseEntity<ApiResponse> updatePassword(@RequestBody @Valid PasswordReq passwordReq,
                                               @PathVariable Long userId);

}
