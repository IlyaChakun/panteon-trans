package by.iba.repository;

import by.iba.domain.Account;
import by.iba.domain.AccountId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends CrudRepository<Account, AccountId> {

    Optional<Account> findByAccountId(Long accountId);

}
