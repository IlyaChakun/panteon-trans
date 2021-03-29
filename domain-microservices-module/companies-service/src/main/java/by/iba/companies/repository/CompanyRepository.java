package by.iba.companies.repository;

import by.iba.companies.domain.Company;
import by.iba.companies.domain.CompanyId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, CompanyId> {

    Optional<Company> findByUNP(final String unp);

}
