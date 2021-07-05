package by.iba.exchange.request.repository;

import by.iba.common.repository.BaseAbstractRepository;
import by.iba.exchange.request.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends BaseAbstractRepository<Customer> {

    Optional<Customer> findCustomerByUNP(String unp);

    boolean existsCustomerByUNP(String UNP);

}
