package by.iba.company.review.service;

import by.iba.common.dto.PageWrapper;
import by.iba.company.review.dto.CompanyReviewDTO;

public interface CompanyReviewService {

    CompanyReviewDTO save(CompanyReviewDTO companyReviewDTO);

    Long deleteById(Long id, Long companyId);

    CompanyReviewDTO findById(Long companyId, Long id);

    PageWrapper<CompanyReviewDTO> findAll(Long companyId, final Integer page, final Integer size);

}
