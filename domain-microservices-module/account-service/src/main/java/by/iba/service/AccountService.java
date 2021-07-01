package by.iba.service;

import by.iba.dto.AccountDTO;
import by.iba.dto.PasswordReqDTO;
import by.iba.dto.UserDTO;

public interface AccountService {

    AccountDTO save(AccountDTO accountDTO);

    AccountDTO findById(Long accountId);

    void confirmAccount(String token);

    void recoverPassword( String userEmail);

    void updatePasswordFromRecovery(String token, PasswordReqDTO passwordReqDTO);

    void updatePassword(Long userId, PasswordReqDTO passwordReqDTO);

}
