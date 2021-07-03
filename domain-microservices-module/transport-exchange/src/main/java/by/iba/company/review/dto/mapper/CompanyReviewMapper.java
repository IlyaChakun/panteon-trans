package by.iba.company.review.dto.mapper;

import by.iba.common.dto.mapper.AbstractMapperDTO;
import by.iba.review.domain.CompanyReview;
import by.iba.review.dto.CompanyReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CompanyReviewMapper extends AbstractMapperDTO<CompanyReview, CompanyReviewDTO> {

    @Autowired
    public CompanyReviewMapper() {
        super(CompanyReview.class, CompanyReviewDTO.class);
    }

}
