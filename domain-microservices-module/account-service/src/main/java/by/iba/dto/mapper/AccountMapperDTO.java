package by.iba.dto.mapper;


import by.iba.common.mapper.core.SimpleAbstractMapper;
import by.iba.domain.Account;
import by.iba.dto.AccountResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperDTO extends SimpleAbstractMapper<Account, AccountResp> {

    @Autowired
    public AccountMapperDTO() {
        super(Account.class, AccountResp.class);
    }
}