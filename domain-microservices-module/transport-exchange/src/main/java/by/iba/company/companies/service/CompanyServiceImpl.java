package by.iba.company.companies.service;

import by.iba.client.AccountClient;
import by.iba.common.dto.PageWrapper;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.common.exception.ServiceException;
import by.iba.company.companies.domain.Company;
import by.iba.company.companies.dto.CompanyCriteria;
import by.iba.company.companies.dto.CompanyReq;
import by.iba.company.companies.dto.CompanyResp;
import by.iba.company.companies.dto.mapper.CompanyMapper;
import by.iba.company.companies.repository.CompanyRepository;
import by.iba.company.companies.repository.specification.CompanySpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final AccountClient accountClient;

    @Override
    @Transactional
    public CompanyResp save(final CompanyReq companyReq) {
        

        validateUniqueUnpEmailPhoneNumbersThrowException(companyReq);

        final Company company = companyMapper.toEntityFromReq(companyReq);

        final Company savedCompany = companyRepository.save(company);

        //

        //create Patch req
        accountClient.addCompanyToAccount(savedCompany.getOwnerId(), savedCompany.getCompanyId());
        //accountClient.patch();
        //сделать в  отдельном потоке
        return companyMapper.toDto(savedCompany);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "companies"),
            @CacheEvict(value = "company-unp", key = "#unp"),
            @CacheEvict(value = "company-id", key = "#result")
    })
    @Transactional
    public Long deleteByUnp(final String unp) {
        

        final Long companyId = companyRepository.findIdByUNP(unp);
        if (Objects.isNull(companyId)) {
            throw new ResourceNotFoundException("exception.company.not_found_exception");
        }

        companyRepository.deleteCompanyByUNP(unp);

        

        return companyId;
    }

    @Override
    @Cacheable(value = "company-unp")
    public CompanyResp findByUnp(final String unp) {
        

        final Company company = getCompanyByUnp(unp);

        

        return companyMapper.toDto(company);
    }

    @Override
    @Cacheable(value = "company-id")
    public CompanyResp findById(final Long id) {
        

        final Company company = getCompanyById(id);
        final CompanyResp companyResp = companyMapper.toDto(company);
        

        return companyResp;
    }

    @Override
    @Cacheable(value = "companies")
    public PageWrapper<CompanyResp> findAll(CompanyCriteria companyCriteria) {

        
        Pageable pageable = companyCriteria.getPageable();//todo fix mistake
        Specification<Company> specification = CompanySpecification.findByFeatureIds(companyCriteria.getCompanyFeatureIds());

        final Page<Company> products = companyRepository.findAll(pageable);

        

        return new PageWrapper<>(
                companyMapper.toDtoList(products.toList()),
                products.getTotalPages(),
                products.getTotalElements());
    }


    private void validateUniqueUnpEmailPhoneNumbersThrowException(final CompanyReq companyReq) {
        if (isUnpExist(companyReq.getUnp())) {
            throw new ServiceException(HttpStatus.CONFLICT.value(), "exception.company.duplicate_unp_error");
        }

        if (isEmailExist(companyReq.getEmail())) {
            throw new ServiceException(HttpStatus.CONFLICT.value(), "exception.company.duplicate_email_error");
        }

        if (arePhoneNumbersExist(companyReq.getPhoneNumbers())) {
            throw new ServiceException(HttpStatus.CONFLICT.value(), "exception.company.duplicate_phone_number_error");
        }

    }

    private boolean isUnpExist(final String unp) {
        return companyRepository.existsCompanyByUNP(unp);
    }

    private boolean isEmailExist(final String email) {
        return companyRepository.existsCompanyByEmail(email);
    }

    private boolean arePhoneNumbersExist(final List<String> phoneNumbers) {
        return phoneNumbers.stream()
                .anyMatch(companyRepository::existsCompanyByPhoneNumbersContains);
    }

    private Company getCompanyById(Long id) {
        return companyRepository.findByCompanyId(id)
                .orElseThrow(() -> new ResourceNotFoundException("exception.company.not_found_exception"));
    }


    private Company getCompanyByUnp(final String unp) {
        return companyRepository.findByUNP(unp)
                .orElseThrow(() -> new ResourceNotFoundException("exception.company.not_found_exception"));
    }
}
