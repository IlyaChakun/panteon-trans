package by.iba.review.specifications;

import by.iba.review.domain.CompanyReview;
import org.springframework.data.jpa.domain.Specification;

public class CompanyReviewSpecifications {

    public static Specification<CompanyReview> reviewNotDeleted(){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNull(root.get("date"));
    }


}
