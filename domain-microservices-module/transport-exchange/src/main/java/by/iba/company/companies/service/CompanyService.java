package by.iba.company.companies.service;

import by.iba.common.dto.PageWrapper;
import by.iba.company.companies.dto.CompanyDTO;

public interface CompanyService {

    CompanyDTO save(final CompanyDTO companyDTO);

    CompanyDTO update(final Long companyId, final CompanyDTO companyDTO);

    Long deleteByUnp(final String unp);

    CompanyDTO findByUnp(final String unp);

    CompanyDTO findById(final Long id);

    PageWrapper<CompanyDTO> findAll(final Integer page, final Integer size);
}