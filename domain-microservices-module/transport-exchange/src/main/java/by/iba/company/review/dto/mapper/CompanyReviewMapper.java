package by.iba.company.review.dto.mapper;

import by.iba.common.mapper.core.SimpleAbstractMapper;
import by.iba.company.review.domain.CompanyReview;
import by.iba.company.review.dto.CompanyReviewResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CompanyReviewMapper extends SimpleAbstractMapper<CompanyReview, CompanyReviewResp> {

    @Autowired
    public CompanyReviewMapper() {
        super(CompanyReview.class, CompanyReviewResp.class);
    }

}
