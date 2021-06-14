package by.iba.blacklist.service;

import by.iba.blacklist.dto.BlacklistDTO;
import by.iba.common.dto.PageWrapper;
import by.iba.companies.domain.Company;

import java.util.List;

public interface BlacklistService {

    BlacklistDTO save(BlacklistDTO blacklistDTO);

    PageWrapper<BlacklistDTO> findAll(final Integer page, final Integer size);

    BlacklistDTO findById(Long id);

    Long delete(BlacklistDTO blacklistDTO);

    PageWrapper<BlacklistDTO> findAllByCompanyId(final Integer page, final Integer size, final Long companyId);

}
