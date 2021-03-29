package by.iba.companies.repository;

import by.iba.companies.domain.Company;
import by.iba.companies.domain.CompanyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, CompanyId> {

    Optional<Company> findByUNP(final String unp);

}
