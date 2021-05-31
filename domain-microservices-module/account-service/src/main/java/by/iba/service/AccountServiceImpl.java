package by.iba.service;

import by.iba.client.AuthServiceClient;
import by.iba.client.CompanyServiceClient;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.domain.Account;
import by.iba.dto.AccountDTO;
import by.iba.dto.CompanyDTO;
import by.iba.dto.UserDTO;
import by.iba.dto.mapper.AccountMapperDTO;
import by.iba.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        log.info("in AccountServiceImpl method create");

        UserDTO savedUser = saveUser(accountDTO);
        //CompanyDTO savedCompany = saveCompany(accountDTO);

        log.info("saved user id={}, email={}", savedUser.getUserId(), savedUser.getEmail());
        log.info("new account has been created: email={} ", savedUser.getEmail());
      //  log.info("saved company id={}, unp={}", savedCompany.getCompanyId(), savedCompany.getUnp());

        Account account = new Account();
        account.setUserId(savedUser.getUserId());

        Account savedAccount = accountRepository.save(account);

        return accountMapper.toDto(savedAccount);
    }

    @Override
    public AccountDTO findById(Long accountId) {
        log.info("in AccountServiceImpl method findById");
        log.info("accountId={}", accountId);

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

    private CompanyDTO saveCompany(AccountDTO accountDTO) {
        return companiesClient.save(accountDTO.getCompany()).getBody();
    }

    private CompanyDTO findCompanyById(final Long companyId) {
        return companiesClient.findById(companyId).getBody();
    }


}
