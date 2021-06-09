package by.iba.review.service;

import by.iba.common.dto.PageWrapper;
import by.iba.review.dto.CompanyReviewDTO;

import java.time.LocalDate;
import java.util.List;

public interface CompanyReviewService {

    CompanyReviewDTO save(CompanyReviewDTO companyReviewDTO);

    Long deleteById(Long id);

    CompanyReviewDTO findById(Long id);

    PageWrapper<CompanyReviewDTO> findAll(Long companyId, final Integer page, final Integer size);

}
