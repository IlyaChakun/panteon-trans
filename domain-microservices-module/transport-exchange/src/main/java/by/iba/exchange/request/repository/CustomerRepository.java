package by.iba.exchange.request.repository;

import by.iba.common.repository.BaseAbstractLongKeyRepository;
import by.iba.exchange.request.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends BaseAbstractLongKeyRepository<Customer> {

    Optional<Customer> findCustomerByUNP(String unp);

    boolean existsCustomerByUNP(String UNP);

}
