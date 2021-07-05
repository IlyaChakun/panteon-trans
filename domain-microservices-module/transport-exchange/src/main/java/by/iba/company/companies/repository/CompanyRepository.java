package by.iba.company.companies.repository;

import by.iba.common.repository.BaseAbstractCompositeKeyRepository;
import by.iba.company.companies.domain.Company;
import by.iba.company.companies.domain.CompositeCompanyId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends BaseAbstractCompositeKeyRepository<Company, CompositeCompanyId> {

    Optional<Company> findByUNP(final String unp);

    Optional<Company> findByCompanyId(final Long companyId);

    boolean existsCompanyByEmail(final String email);

    boolean existsCompanyByUNP(final String unp);

    void deleteCompanyByUNP(final String unp);

    @Query("SELECT c FROM Company c WHERE c.UNP = ?1")
    Long findIdByUNP(final String unp);

    boolean existsCompanyByPhoneNumbersContains(final String phoneNumber);
}
