package by.iba.companies.repository;

import by.iba.companies.domain.PhoneNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends CrudRepository<PhoneNumber, String> {

    boolean existsPhoneNumberByValue(final String value);

}
