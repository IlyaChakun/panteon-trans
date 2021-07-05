package by.iba.company.review.controller;


import by.iba.common.dto.PageWrapper;
import by.iba.company.review.dto.CompanyReviewDTO;
import by.iba.company.review.service.CompanyReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@AllArgsConstructor
@Slf4j
public class CompanyReviewControllerImpl implements CompanyReviewController {

    private final CompanyReviewService companyReviewService;

    @Override
    public ResponseEntity<CompanyReviewDTO> findById(Long companyId, Long id) {
        log.info("Received a request to find review by id = {} and company id ={}", id, companyId);

        CompanyReviewDTO companyReviewDTO = companyReviewService.findById(companyId, id);

        return ResponseEntity
                .ok()
                .body(companyReviewDTO);
    }

    @Override
    public ResponseEntity<PageWrapper<CompanyReviewDTO>> findAll(Long companyId, Integer page, Integer size) {

        log.info("Received a request to find all reviews for company with id = {} ", companyId);


        PageWrapper<CompanyReviewDTO> allReviews =
                companyReviewService.findAll(companyId, page, size);//TODO: admin option to find deleted reviews


        return ResponseEntity
                .ok()
                .body(allReviews);
    }

    @Override
    public ResponseEntity<CompanyReviewDTO> save(CompanyReviewDTO companyReviewDTO, Long companyId) {
        log.info("Received a request to save rating of the company with id = {}", companyId);

        CompanyReviewDTO savedReview = companyReviewService.save(companyReviewDTO);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/companies/{companyId}/rating/{id}")
                .buildAndExpand(companyId, savedReview.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(savedReview);
    }

    @Override
    public ResponseEntity<Void> delete(Long id, Long companyId) {
        log.info("Received a request to delete review with id = {} from company with id = {}", id,companyId);

        companyReviewService.deleteById(id, companyId);

        return ResponseEntity
                .noContent()
                .build();
    }

}