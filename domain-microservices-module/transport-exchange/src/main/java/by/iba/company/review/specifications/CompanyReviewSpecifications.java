package by.iba.company.review.specifications;

import by.iba.company.review.domain.CompanyReview;
import org.springframework.data.jpa.domain.Specification;

public class CompanyReviewSpecifications {

    public static Specification<CompanyReview> reviewNotDeleted() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNull(root.get("date"));
    }

    public static Specification<CompanyReview> hasCompanyId(Long companyId) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("companyId"), companyId));
    }

}
