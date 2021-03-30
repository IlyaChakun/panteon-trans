package by.iba.companies.service;

import by.iba.common.dto.PageWrapper;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.common.exception.ServiceException;
import by.iba.companies.domain.Company;
import by.iba.companies.dto.CompanyDTO;
import by.iba.companies.dto.mapper.CompanyMapperDTO;
import by.iba.companies.repository.CompanyRepository;
import by.iba.companies.repository.PhoneNumberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final PhoneNumberRepository phoneNumberRepository;
    private final CompanyMapperDTO companyMapper;

    @Override
    public CompanyDTO save(final CompanyDTO companyDTO) {
        log.info("Start saving the company with UNP = {}", companyDTO.getUNP());

        validateUniqueUnpEmailPhoneNumbersThrowException(companyDTO);
        companyRepository.save(companyMapper.toEntity(companyDTO));

        log.info("Finish saving the company with UNP = {}", companyDTO.getUNP());

        return companyDTO;
    }

    private void validateUniqueUnpEmailPhoneNumbersThrowException(final CompanyDTO companyDTO) {
        if (isUnpExist(companyDTO.getUNP())) {
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
        return phoneNumbers.stream().anyMatch(phoneNumberRepository::existsPhoneNumberByValue);
    }

    @Override
    public CompanyDTO update(final String unp, final CompanyDTO companyDTO) {
        return null;
    }

    @Override
    public void delete(final String unp) {
        log.info("Start deleting the company by UNP = {}", unp);

        companyRepository.delete(getCompanyByUNP(unp));

        log.info("Company with unp = {} has been deleted!", unp);
    }

    @Override
    public CompanyDTO findByUNP(final String unp) {
        log.info("Start finding the company by UNP = {}", unp);

        final Company company = getCompanyByUNP(unp);

        log.info("Company with unp = {} has been found!", unp);

        return companyMapper.toDto(company);
    }

    @Override
    public CompanyDTO findById(final Long id) {
        log.info("Start finding the company by id = {}", id);

        final Company company = companyRepository.findByCompanyId(id)
                .orElseThrow(() -> new ResourceNotFoundException("exception.company.not_found_exception"));
        final CompanyDTO companyDTO = companyMapper.toDto(company);
        log.info("Company with id = {} has been found!", id);

        return companyDTO;
    }

    @Override
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

    private Company getCompanyByUNP(final String unp) {
        return companyRepository.findByUNP(unp)
                .orElseThrow(() -> new ResourceNotFoundException("exception.company.not_found_exception"));
    }
}
