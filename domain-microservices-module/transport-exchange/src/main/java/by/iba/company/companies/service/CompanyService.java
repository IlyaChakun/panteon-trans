package by.iba.company.companies.service;

import by.iba.common.dto.PageWrapper;
import by.iba.company.companies.dto.CompanyCriteria;
import by.iba.company.companies.dto.CompanyReq;
import by.iba.company.companies.dto.CompanyResp;

public interface CompanyService {

    CompanyResp save(final CompanyReq companyReq);

    Long deleteByUnp(final String unp);

    CompanyResp findByUnp(final String unp);

    CompanyResp findById(final Long id);

    PageWrapper<CompanyResp> findAll(CompanyCriteria companyCriteria);
}
