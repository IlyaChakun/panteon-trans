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
import by.iba.dto.mapper.AccountMapperDTO;
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
    private final AccountMapperDTO accountMapper;

    @Override
    @Transactional
    public AccountResp save(AccountReq accountReq) {

        UserResp savedUser = saveUser(accountReq);

        log.info("Saved user id = {}, email = {}", savedUser.getUserId(), savedUser.getEmail());

        Account account = new Account();
        account.setUserId(savedUser.getUserId());
        Account savedAccount = accountRepository.save(account);

        return accountMapper.toDto(savedAccount);
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
    public AccountResp findById(Long accountId) {
        log.info("Finding account with id = {}", accountId);

        Account account = accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("exception.account.not_found_exception"));

        log.info("account with id={} has been found!", accountId);

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
        log.info("Password recovery started");
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

    private CompanyResp saveCompany(AccountResp accountDTO) {
        return companiesClient.save(accountDTO.getCompany()).getBody();
    }

    private CompanyResp findCompanyById(final Long companyId) {
        return companiesClient.findById(companyId).getBody();
    }


}
