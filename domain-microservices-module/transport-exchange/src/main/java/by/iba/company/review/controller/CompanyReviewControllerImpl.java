package by.iba.company.review.controller;


import by.iba.common.dto.PageWrapper;
import by.iba.company.review.dto.CompanyReviewResp;
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
    public ResponseEntity<CompanyReviewResp> findById(Long companyId, Long id) {
        

        CompanyReviewResp companyReviewDTO = companyReviewService.findById(companyId, id);

        return ResponseEntity
                .ok()
                .body(companyReviewDTO);
    }

    @Override
    public ResponseEntity<PageWrapper<CompanyReviewResp>> findAll(Long companyId, Integer page, Integer size) {

        


        PageWrapper<CompanyReviewResp> allReviews =
                companyReviewService.findAll(companyId, page, size);//TODO: admin option to find deleted reviews


        return ResponseEntity
                .ok()
                .body(allReviews);
    }

    @Override
    public ResponseEntity<CompanyReviewResp> save(CompanyReviewResp companyReviewDTO, Long companyId) {
        

        CompanyReviewResp savedReview = companyReviewService.save(companyReviewDTO);

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
        

        companyReviewService.deleteById(id, companyId);

        return ResponseEntity
                .noContent()
                .build();
    }

}
