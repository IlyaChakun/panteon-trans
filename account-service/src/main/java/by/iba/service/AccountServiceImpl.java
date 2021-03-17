package by.iba.service;

import by.iba.client.AuthServiceClient;
import by.iba.domain.Account;
import by.iba.dto.AccountDTO;
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

    private final AccountRepository accountRepository;
    private final AccountMapperDTO accountMapper;


    @Override
    public AccountDTO create(AccountDTO accountDTO) {
        log.info("in AccountServiceImpl method create");

        UserDTO savedUser = saveUser(accountDTO);

        log.info("saved user id={}, email={}", savedUser.getUserId(), savedUser.getEmail());
        log.info("new account has been created: email={} ", savedUser.getEmail());

        Account account = new Account();
        account.setUserId(1L);
        account.setCompanyId(1L);

        Account savedAccount = accountRepository.save(account);

        return accountMapper.toDto(savedAccount);
    }

    private UserDTO saveUser(AccountDTO accountDTO) {
        return authClient.save(accountDTO.getUser()).getBody();
    }


}
