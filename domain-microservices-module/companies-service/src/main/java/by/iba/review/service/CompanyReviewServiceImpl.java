package by.iba.review.service;

import by.iba.common.dto.PageWrapper;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.review.domain.CompanyReview;
import by.iba.review.dto.CompanyReviewDTO;
import by.iba.review.dto.mapper.CompanyReviewMapper;
import by.iba.review.repository.CompanyReviewRepository;
import by.iba.review.specifications.CompanyReviewSpecifications;
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
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CompanyReviewServiceImpl implements CompanyReviewService {

    private final CompanyReviewRepository companyReviewRepository;
    private final CompanyReviewMapper companyReviewMapper;

    @Override
    @Caching(put = {
            @CachePut(value = "company_id", key = "#companyReviewMapper.toEntity(companyReviewDTO).getCompanyId()"),
            @CachePut(value = "review_id", key = "#companyReviewMapper.toEntity(companyReviewDTO).getCompanyId()")

    })
    @Transactional
    public CompanyReviewDTO save(CompanyReviewDTO companyReviewDTO) {
        log.info("Start saving the company review with id = {}",
                companyReviewMapper
                        .toEntity(companyReviewDTO)
                        .getId());

        CompanyReview savedCompanyReview = companyReviewMapper.toEntity(companyReviewDTO);

        companyReviewRepository.save(savedCompanyReview);

        log.info("Finish saving the company review with id = {}", companyReviewMapper
                .toEntity(companyReviewDTO)
                .getId());

        return companyReviewMapper.toDto(savedCompanyReview);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "company_id", key = "#result"),
            @CacheEvict(value = "review_id", key = "#id")

    })
    public Long deleteById(Long id) {

        log.info("Start deleting the company review with id = {} ", id);

        CompanyReview review = companyReviewRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("review with id = " + id + " not found "));

        companyReviewRepository.delete(review);

        log.info("Review with id = {} has been deleted ", id);

        return id;
    }

    @Override
    public CompanyReviewDTO findById(Long id) {
        log.info("Finding review by id = {}", id);

        CompanyReview companyReview = companyReviewRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("review with id = " + id + " not found "));

        return companyReviewMapper
                .toDto(companyReview);
    }

    @Override
    public PageWrapper<CompanyReviewDTO> findAll(Long companyId, final Integer page, final Integer size) {

        Specification<CompanyReview> specification = Specification
                .where(CompanyReviewSpecifications
                        .reviewNotDeleted())
                .and(CompanyReviewSpecifications
                        .hasCompanyId(companyId));

        final Pageable pageable =
                PageRequest.of(page, size);

        final Page<CompanyReview> companyReviews =
                companyReviewRepository.findAll(specification, pageable);

        return new PageWrapper<>(companyReviewMapper
                .toDtoList(companyReviews.toList()),
                companyReviews.getTotalPages(),
                companyReviews.getTotalElements());
    }

}
