package by.iba.company.companies.repository;

import by.iba.companies.domain.Company;
import by.iba.companies.domain.CompanyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, CompanyId> {

    Optional<Company> findByUNP(final String unp);

    Optional<Company> findByCompanyId(final Long companyId);

    boolean existsCompanyByEmail(final String email);

    boolean existsCompanyByUNP(final String unp);

    void deleteCompanyByUNP(final String unp);

    @Query("SELECT c.companyId FROM Company c WHERE c.UNP = ?1")
    Long findIdByUNP(final String unp);

    boolean existsCompanyByPhoneNumbersContains(final String phoneNumber);
}
