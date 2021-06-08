package by.iba.review.service;

import by.iba.review.dto.CompanyReviewDTO;

import java.time.LocalDate;
import java.util.List;

public interface CompanyReviewService {

    CompanyReviewDTO save(CompanyReviewDTO companyReviewDTO);

    Long deleteById(Long id);

    List<CompanyReviewDTO> findByCompanyId(Long id);

    CompanyReviewDTO findById(Long id);


    List<CompanyReviewDTO>findAll();

}
