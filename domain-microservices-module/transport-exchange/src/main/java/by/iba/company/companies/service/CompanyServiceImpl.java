package by.iba.company.companies.service;

import by.iba.common.dto.PageWrapper;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.common.exception.ServiceException;
import by.iba.company.companies.domain.Company;
import by.iba.company.companies.dto.CompanyDTO;
import by.iba.company.companies.dto.mapper.CompanyMapperDTO;
import by.iba.company.companies.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {


    private final CompanyRepository companyRepository;
    private final CompanyMapperDTO companyMapper;

    @Override
    public CompanyDTO save(final CompanyDTO companyDTO) {
        log.info("Start saving the company with UNP = {}", companyDTO.getUnp());

        validateUniqueUnpEmailPhoneNumbersThrowException(companyDTO);

        final Company company = companyMapper.toEntity(companyDTO);
        company.setCountryId(companyDTO.getCountryId());

        final Company savedCompany = companyRepository.save(company);

        log.info("Finish saving the company with UNP = {}", savedCompany.getUNP());

        return companyMapper.toDto(savedCompany);
    }

    private void validateUniqueUnpEmailPhoneNumbersThrowException(final CompanyDTO companyDTO) {
        if (isUnpExist(companyDTO.getUnp())) {
            throw new ServiceException(HttpStatus.CONFLICT.value(), "exception.company.duplicate_unp_error");
        }

        if (isEmailExist(companyDTO.getEmail())) {
            throw new ServiceException(HttpStatus.CONFLICT.value(), "exception.company.duplicate_email_error");
        }

        if (arePhoneNumbersExist(companyDTO.getPhoneNumbers())) {
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

    @Override
    @Caching(put = {
            @CachePut(value = "companies"),
            @CachePut(value = "company-unp", key = "#companyDTO.unp"),
            @CachePut(value = "company-id", key = "#companyId")
    })
    public CompanyDTO update(final Long companyId, final CompanyDTO companyDTO) {
        log.info("Start updating the company by id = {}", companyId);

        final Company company = getCompanyById(companyId);
        updateCompanyFields(company, companyDTO);

        final Company savedCompany = companyRepository.save(company);
        log.info("Company with id = {} has been updated!", savedCompany.getCompanyId());

        return companyMapper.toDto(savedCompany);

    }

    private void updateCompanyFields(Company company, CompanyDTO companyDTO) {
        company.setEmail(companyDTO.getEmail());
        company.setSite(companyDTO.getSite());
        company.setDescription(companyDTO.getDescription());
        company.setTitle(companyDTO.getTitle());
        company.setAddress(companyDTO.getAddress());
        companyDTO.getPhoneNumbers()
                .forEach(phoneNumber -> company.getPhoneNumbers()
                        .add(phoneNumber)
                );
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "companies"),
            @CacheEvict(value = "company-unp", key = "#unp"),
            @CacheEvict(value = "company-id", key = "#result")
    })
    public Long deleteByUnp(final String unp) {
        log.info("Start deleting the company by UNP = {}", unp);

        final Long companyId = companyRepository.findIdByUNP(unp);
        if (companyId == null) {
            throw new ResourceNotFoundException("exception.company.not_found_exception");
        }

        companyRepository.deleteCompanyByUNP(unp);

        log.info("Company with unp = {} has been deleted!", unp);

        return companyId;
    }

    @Override
    @Cacheable(value = "company-unp")
    public CompanyDTO findByUnp(final String unp) {
        log.info("Start finding the company by UNP = {}", unp);

        final Company company = getCompanyByUnp(unp);

        log.info("Company with unp = {} has been found!", unp);

        return companyMapper.toDto(company);
    }

    private Company getCompanyByUnp(final String unp) {
        return companyRepository.findByUNP(unp)
                .orElseThrow(() -> new ResourceNotFoundException("exception.company.not_found_exception"));
    }

    @Override
    @Cacheable(value = "company-id")
    public CompanyDTO findById(final Long id) {
        log.info("Start finding the company by id = {}", id);

        final Company company = getCompanyById(id);
        final CompanyDTO companyDTO = companyMapper.toDto(company);
        log.info("Company with id = {} has been found!", id);

        return companyDTO;
    }

    private Company getCompanyById(Long id) {
        return companyRepository.findByCompanyId(id)
                .orElseThrow(() -> new ResourceNotFoundException("exception.company.not_found_exception"));
    }

    @Override
    @Cacheable(value = "companies")
    public PageWrapper<CompanyDTO> findAll(final Integer page, final Integer size) {

        log.info("Start finding all companies");

        final Pageable pageable =
                PageRequest.of(page, size);
        final Page<Company> products = companyRepository.findAll(pageable);

        log.info("Finish finding all companies");

        return new PageWrapper<>(
                companyMapper.toDtoList(products.toList()),
                products.getTotalPages(),
                products.getTotalElements());
    }

}
