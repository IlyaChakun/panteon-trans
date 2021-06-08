package by.iba.review.service;

import by.iba.review.dto.CompanyReviewDTO;

import java.time.LocalDate;
import java.util.List;

public interface CompanyReviewService {

    CompanyReviewDTO saveReview(CompanyReviewDTO companyReviewDTO);

    Long deleteReviewById(Long id);

    List<CompanyReviewDTO> findReviewsByCompanyId(Long id);

    CompanyReviewDTO findReviewById(Long id);


    List<CompanyReviewDTO>findAll(LocalDate date);

}
