package by.iba.dto.mapper;


import by.iba.common.mapper.core.FullAbstractMapper;
import by.iba.domain.Account;
import by.iba.dto.AccountReq;
import by.iba.dto.AccountResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper extends FullAbstractMapper<Account, AccountResp, AccountReq> {

    @Autowired
    public AccountMapper() {
        super(Account.class, AccountResp.class);
    }
}