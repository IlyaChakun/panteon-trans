package by.iba.company.review.service;

import by.iba.common.dto.PageWrapper;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.company.review.domain.CompanyReview;
import by.iba.company.review.dto.CompanyReviewResp;
import by.iba.company.review.dto.mapper.CompanyReviewMapper;
import by.iba.company.review.repository.CompanyReviewRepository;
import by.iba.company.review.specifications.CompanyReviewSpecifications;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@AllArgsConstructor
@Slf4j
public class CompanyReviewServiceImpl implements CompanyReviewService {

    private final CompanyReviewRepository companyReviewRepository;
    private final CompanyReviewMapper companyReviewMapper;

    @Override
    @Caching(put = {
            @CachePut(value = "company_id", key = "#companyReviewDTO.getCompanyId()"),
            @CachePut(value = "review_id", key = "#companyReviewDTO.getCompanyId()")

    })
    @Transactional
    public CompanyReviewResp save(CompanyReviewResp companyReviewDTO) {
        
                companyReviewMapper
                        .toEntity(companyReviewDTO)
                        .getId());

        CompanyReview savedCompanyReview = companyReviewMapper.toEntity(companyReviewDTO);

        companyReviewRepository.save(savedCompanyReview);

        
                .toEntity(companyReviewDTO)
                .getId());

        return companyReviewMapper.toDto(savedCompanyReview);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "company_id", key = "#result"),
            @CacheEvict(value = "review_id", key = "#id")

    })
    public Long deleteById(Long id, Long companyId) {

        

        CompanyReview review = companyReviewRepository
                .findByIdAndCompanyId(id, companyId)
                .orElseThrow(() -> new ResourceNotFoundException("review with id = " + id + " not found "));

        review.setDate(LocalDate.now());
        companyReviewRepository.save(review);

        

        return companyId;
    }

    @Override
    public CompanyReviewResp findById(Long companyId, Long id) {
        

        CompanyReview companyReview = companyReviewRepository
                .findByIdAndCompanyId(id, companyId)
                .orElseThrow(() -> new ResourceNotFoundException("review with id = " + id + " not found "));

        return companyReviewMapper
                .toDto(companyReview);
    }

    @Override
    public PageWrapper<CompanyReviewResp> findAll(Long companyId, final Integer page, final Integer size) {

        Specification<CompanyReview> specification = Specification
                .where(CompanyReviewSpecifications
                        .reviewNotDeleted())
                .and(CompanyReviewSpecifications
                        .hasCompanyId(companyId));

        final Pageable pageable =
                PageRequest.of(page, size);

        final Page<CompanyReview> companyReviews =
                companyReviewRepository.findAll(specification, pageable);

        return
                new PageWrapper<>(companyReviewMapper
                        .toDtoList(companyReviews.toList()),
                        companyReviews.getTotalPages(),
                        companyReviews.getTotalElements());
    }

}
