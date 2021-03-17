package by.iba.dto.mapper;


import by.iba.domain.Account;
import by.iba.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperDTO extends AbstractMapperDTO<Account, AccountDTO> {

    @Autowired
    public AccountMapperDTO() {
        super(Account.class, AccountDTO.class);
    }
}