package by.iba.service;

import by.iba.client.AuthServiceClient;
import by.iba.client.CompanyServiceClient;
import by.iba.client.dto.CompanyResp;
import by.iba.client.dto.UserResp;
import by.iba.common.dto.PatchReq;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.common.patch.PatchUtil;
import by.iba.domain.Account;
import by.iba.dto.AccountReq;
import by.iba.dto.AccountResp;
import by.iba.dto.PasswordReq;
import by.iba.dto.mapper.AccountMapper;
import by.iba.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AuthServiceClient authClient;
    private final CompanyServiceClient companiesClient;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    @Transactional
    public AccountResp save(AccountReq accountReq) {

        UserResp savedUser = saveUser(accountReq);

        Account account = new Account();
        account.setUserId(savedUser.getUserId());
        Account savedAccount = accountRepository.save(account);

        AccountResp accountResp = accountMapper.toDto(savedAccount);
        //accountResp.setUser(authClient.);
        return accountResp;
    }

    @Override
    @Transactional
    public AccountResp partialUpdate(PatchReq patch, Long id) {
        Account account =
                accountRepository.findByAccountId(id)
                        .orElseThrow(() -> new ResourceNotFoundException("exception.request.not_found_by_id"
                                + id));

        Account mappedReq = PatchUtil.applyPatchToRequest(patch, account);
        Account savedReq = accountRepository.save(mappedReq);

        return accountMapper.toDto(savedReq);
    }

    @Override
    public AccountResp addCompanyToAccount(Long id, Long companyId) {

        Account account = accountRepository.findByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundException("exception.request.not_found_by_user_id"));

        account.setCompanyId(companyId);

        Account savedAccount = accountRepository.save(account);

        return accountMapper.toDto(savedAccount);
    }

    @Override
    public AccountResp findById(Long accountId) {

        Account account = accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("exception.account.not_found_exception"));

        final AccountResp accountDTO = accountMapper.toDto(account);

        final CompanyResp companyDTO = findCompanyById(account.getCompanyId());
        accountDTO.setCompany(companyDTO);

        return accountDTO;
    }

    private UserResp saveUser(AccountReq accountReq) {
        return authClient.save(accountReq.getUser()).getBody();
    }

    @Override
    @Transactional
    public void confirmAccount(String token) {
        log.error("this method is not implemented");
        throw new NotYetImplementedException("Not implemented");
        // authClient.confirmUserAccount(token);
    }

    @Override
    @Transactional
    public void recoverPassword(String userEmail) {

        sendRecoverPasswordMessage(userEmail);
        //update pass
        //send message suc.
        //1. old!=new
        //2. new==conf
    }

    @Override
    @Transactional
    public void updatePasswordFromRecovery(String token, PasswordReq passwordReq) {
        authClient.recoverPasswordConfirmation(token, passwordReq);
    }

    @Override
    @Transactional
    public void updatePassword(Long userId, PasswordReq passwordReq) {
        authClient.updatePassword(passwordReq, userId);
    }

    private void sendRecoverPasswordMessage(String userEmail) {
        authClient.recoverPassword(userEmail);
    }

   /* private CompanyResp saveCompanyWithClient(CompanyReq companyReq) {
        return companiesClient.save(companyReq).getBody();
    }*/

    private CompanyResp findCompanyById(final Long companyId) {
        return companiesClient.findById(companyId).getBody();
    }

}
