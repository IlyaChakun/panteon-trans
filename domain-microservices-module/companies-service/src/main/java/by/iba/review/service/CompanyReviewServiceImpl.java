package by.iba.review.service;

import by.iba.common.exception.ResourceNotFoundException;
import by.iba.review.domain.CompanyReview;
import by.iba.review.dto.CompanyReviewDTO;
import by.iba.review.dto.mapper.CompanyReviewMapper;
import by.iba.review.repository.CompanyReviewRepository;
import by.iba.review.specifications.CompanyReviewSpecifications;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public CompanyReviewDTO saveReview(CompanyReviewDTO companyReviewDTO) {
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
    public Long deleteReviewById(Long id) {

        log.info("Start deleting the company review with id = {} ", id);
        final Long companyId = companyReviewRepository.findById(id).get().getCompanyId();

        if (companyId == null)
            throw new ResourceNotFoundException("exception.review.not_found_exception");

        companyReviewRepository.deleteById(id);

        log.info("Review with id = {} has been deleted ", id);

        return companyId;
    }

    @Override
    public List<CompanyReviewDTO> findReviewsByCompanyId(Long id) {

        log.info("Finding reviews by company id = {}", id);

        List<CompanyReview> companyReviews =
                companyReviewRepository.findDistinctByCompanyId(id);
        if (companyReviews.isEmpty())
            throw new ResourceNotFoundException("exception.reviews.not_found_exception");

        List<CompanyReviewDTO> companyReviewDTOS = new ArrayList<>();
        companyReviews.forEach(companyReview -> companyReviewDTOS.add(companyReviewMapper.toDto(companyReview)));

        return companyReviewDTOS;
    }

    @Override
    public CompanyReviewDTO findReviewById(Long id) {
        log.info("Finding review by id = {}", id);

        CompanyReviewDTO foundReview = companyReviewMapper.toDto(companyReviewRepository.findById(id).get());
        if (foundReview == null)
            throw new ResourceNotFoundException("exception.review.not_found_exception");


        return foundReview;
    }

    @Override
    public List<CompanyReviewDTO> findAll(LocalDate date) {

        companyReviewRepository.findAll(Specification.where(CompanyReviewSpecifications.reviewNotDeleted()));

        List<CompanyReview> companyReviews =
                companyReviewRepository.findAll();

        List<CompanyReviewDTO> companyReviewDTOS = new ArrayList<>();
        companyReviews.forEach(companyReview -> companyReviewDTOS.add(companyReviewMapper.toDto(companyReview)));

        return companyReviewDTOS;
    }

}
