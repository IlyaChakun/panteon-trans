package by.iba.service;

import by.iba.dto.AccountResp;
import by.iba.dto.PasswordReqDTO;

public interface AccountService {

    AccountResp save(AccountResp accountDTO);

    AccountResp findById(Long accountId);

    void confirmAccount(String token);

    void recoverPassword( String userEmail);

    void updatePasswordFromRecovery(String token, PasswordReqDTO passwordReqDTO);

    void updatePassword(Long userId, PasswordReqDTO passwordReqDTO);

}
