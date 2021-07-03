package by.iba.company.blacklist.service;

import by.iba.blacklist.dto.BlacklistDTO;
import by.iba.blacklist.dto.BlacklistDeleteDTO;
import by.iba.common.dto.PageWrapper;

public interface BlacklistService {

    BlacklistDTO save(BlacklistDTO blacklistDTO);

    PageWrapper<BlacklistDTO> findAll(final Integer page, final Integer size);

    BlacklistDTO findById(Long id);

    Long delete(BlacklistDeleteDTO blacklistDeleteDTO, final Long id);

    PageWrapper<BlacklistDTO> findAllByCompanyId(final Integer page, final Integer size, final Long companyId);

}
