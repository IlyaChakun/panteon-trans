package by.iba.service;

import by.iba.common.dto.PatchReq;
import by.iba.dto.AccountReq;
import by.iba.dto.AccountResp;
import by.iba.dto.PasswordReq;

public interface AccountService {

    AccountResp save(AccountReq accountReq);

    AccountResp findById(Long accountId);

    void confirmAccount(String token);

    void recoverPassword( String userEmail);

    void updatePasswordFromRecovery(String token, PasswordReq passwordReq);

    void updatePassword(Long userId, PasswordReq passwordReq);

    AccountResp partialUpdate(PatchReq patch, Long id);

    AccountResp addCompanyToAccount(Long id, Long companyId);
   // CompanyResp saveCompany(CompanyReq companyReq);

}
