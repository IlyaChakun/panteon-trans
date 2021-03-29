package by.iba.companies.service;

import by.iba.common.dto.PageWrapper;
import by.iba.companies.dto.CompanyDTO;

public interface CompanyService {

    CompanyDTO save(final CompanyDTO companyDTO);

    CompanyDTO update(final String unp, final CompanyDTO companyDTO);

    void delete(final String unp);

    CompanyDTO findByUNP(final String unp);

    CompanyDTO findById(final Long id);

    PageWrapper<CompanyDTO> findAll();
}
