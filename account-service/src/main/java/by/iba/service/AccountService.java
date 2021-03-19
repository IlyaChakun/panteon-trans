package by.iba.service;

import by.iba.dto.AccountDTO;

public interface AccountService {

    AccountDTO save(AccountDTO accountDTO);

    AccountDTO findById(Long accountId);

}
