package by.iba.repository;

import by.iba.common.repository.BaseAbstractCompositeKeyRepository;
import by.iba.domain.Account;
import by.iba.domain.AccountId;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends BaseAbstractCompositeKeyRepository<Account, AccountId> {

    Optional<Account> findByAccountId(Long accountId);

    Optional<Account> findByUserId(Long userId);

}
