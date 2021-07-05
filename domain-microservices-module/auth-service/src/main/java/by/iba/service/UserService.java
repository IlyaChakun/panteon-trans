package by.iba.service;

import by.iba.dto.PasswordDTO;
import by.iba.dto.UserResp;

public interface UserService {

    UserResp save(UserResp userDTO);

    void confirmUserAccount(final String confirmationToken);

    UserResp recoverPasswordWithToken(String confirmationToken, PasswordDTO passwordDTO);

    void sendRecoverMessage(String email);

    UserResp updatePassword(Long userId, PasswordDTO passwordDTO);
}
