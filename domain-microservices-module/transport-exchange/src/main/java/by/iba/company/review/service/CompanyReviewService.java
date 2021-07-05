package by.iba.company.review.service;

import by.iba.common.dto.PageWrapper;
import by.iba.company.review.dto.CompanyReviewResp;

public interface CompanyReviewService {

    CompanyReviewResp save(CompanyReviewResp companyReviewDTO);

    Long deleteById(Long id, Long companyId);

    CompanyReviewResp findById(Long companyId, Long id);

    PageWrapper<CompanyReviewResp> findAll(Long companyId, final Integer page, final Integer size);

}
