package by.iba.review.repository;

import by.iba.review.domain.CompanyReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyReviewRepository extends JpaRepository<CompanyReview, Long> {


    @Query("SELECT distinct c from CompanyReview c where c.companyId = ?1")
    List<CompanyReview> findReviewsByCompanyId(Long id);


}
