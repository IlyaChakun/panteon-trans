package by.iba.company.review.repository;

import by.iba.company.review.domain.CompanyReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyReviewRepository extends JpaRepository<CompanyReview, Long>, JpaSpecificationExecutor<CompanyReview> {

    Optional<CompanyReview> findByIdAndCompanyId(Long id, Long companyId);

}
