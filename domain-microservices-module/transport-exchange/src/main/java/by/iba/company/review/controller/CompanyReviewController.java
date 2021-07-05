package by.iba.company.review.controller;

import by.iba.common.dto.PageWrapper;
import by.iba.company.review.dto.CompanyReviewResp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/companies/{companyId}/reviews")
@CrossOrigin(origins = "*")
public interface CompanyReviewController {

    @GetMapping("/{id}")
    ResponseEntity<CompanyReviewResp> findById(@PathVariable("companyId") final Long companyId,
                                               @PathVariable("id") final Long id);

    @GetMapping()
    ResponseEntity<PageWrapper<CompanyReviewResp>> findAll(@PathVariable("companyId") final Long companyId,
                                                           @RequestParam(defaultValue = "0", required = false) final Integer page,
                                                           @RequestParam(defaultValue = "10", required = false) final Integer size);

    @PostMapping()
    ResponseEntity<CompanyReviewResp> save(CompanyReviewResp companyReviewDTO,
                                           @PathVariable("companyId") final Long companyId);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") final Long id,@PathVariable("companyId") final Long companyId);
}
