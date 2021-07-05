package by.iba.company.blacklist.service;

import by.iba.company.blacklist.dto.BlacklistResp;
import by.iba.company.blacklist.dto.BlacklistDeleteResp;
import by.iba.common.dto.PageWrapper;

public interface BlacklistService {

    BlacklistResp save(BlacklistResp blacklistDTO);

    PageWrapper<BlacklistResp> findAll(final Integer page, final Integer size);

    BlacklistResp findById(Long id);

    Long delete(BlacklistDeleteResp blacklistDeleteDTO, final Long id);

    PageWrapper<BlacklistResp> findAllByCompanyId(final Integer page, final Integer size, final Long companyId);

}
