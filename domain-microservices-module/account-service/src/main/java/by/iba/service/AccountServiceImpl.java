package by.iba.service;

import by.iba.client.AuthServiceClient;
import by.iba.client.CompanyServiceClient;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.domain.Account;
import by.iba.dto.AccountDTO;
import by.iba.dto.CompanyDTO;
import by.iba.dto.PasswordReqDTO;
import by.iba.dto.UserDTO;
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
    public AccountDTO save(AccountDTO accountDTO) {
        log.info("Creating account with id = {}", accountDTO.getAccountId());

        UserDTO savedUser = saveUser(accountDTO);

        //CompanyDTO savedCompany = saveCompany(accountDTO);

        log.info("Saved user id = {}, email = {}", savedUser.getUserId(), savedUser.getEmail());
        log.info("New account has been created: email = {} ", savedUser.getEmail());
        //  log.info("saved company id={}, unp={}", savedCompany.getCompanyId(), savedCompany.getUnp());

        Account account = new Account();
        account.setUserId(savedUser.getUserId());//account.setCompanyId(savedCompany.getCompanyId());
        account.setCompanyId(accountDTO.getCompany().getCompanyId());
        Account savedAccount = accountRepository.save(account);

        return accountMapper.toDto(savedAccount);
    }

    @Override
    public AccountDTO findById(Long accountId) {
        log.info("Finding account with id = {}", accountId);

        Account account = accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("exception.account.not_found_exception"));

        log.info("account with id={} has been found!", accountId);

        final AccountDTO accountDTO = accountMapper.toDto(account);

        final CompanyDTO companyDTO = findCompanyById(account.getCompanyId());
        accountDTO.setCompany(companyDTO);

        return accountDTO;
    }

    private UserDTO saveUser(AccountDTO accountDTO) {
        return authClient.save(accountDTO.getUser()).getBody();
    }

    public void confirmAccount(String token) {
        log.error("this method is not implemented");
        throw new NotYetImplementedException("Not implemented");
        // authClient.confirmUserAccount(token);
    }

    @Override
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
    public void updatePassword(String token, PasswordReqDTO passwordReqDTO) {
        authClient.recoverPasswordConfirmation(token, passwordReqDTO);
    }


    public void sendRecoverPasswordMessage(String userEmail) {
        authClient.recoverPassword(userEmail);
    }

    private CompanyDTO saveCompany(AccountDTO accountDTO) {
        return companiesClient.save(accountDTO.getCompany()).getBody();
    }

    private CompanyDTO findCompanyById(final Long companyId) {
        return companiesClient.findById(companyId).getBody();
    }


}
