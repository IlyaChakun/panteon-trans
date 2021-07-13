package by.iba.company.blacklist.service;

import by.iba.common.dto.PageWrapper;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.common.exception.ServiceException;
import by.iba.company.blacklist.domain.Blacklist;
import by.iba.company.blacklist.dto.BlacklistDeleteResp;
import by.iba.company.blacklist.dto.BlacklistResp;
import by.iba.company.blacklist.dto.mapper.BlacklistMapperDTO;
import by.iba.company.blacklist.repository.BlacklistRepository;
import by.iba.company.blacklist.scpecifications.BlacklistSpecifications;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Service
@Slf4j
@AllArgsConstructor
public class BlacklistServiceImpl implements BlacklistService {

    private final BlacklistRepository blacklistRepository;
    private final BlacklistMapperDTO blacklistMapperDTO;

    @Override
//    @Caching(put = {
//            @CachePut(value = "company_id", key = "#blacklistDTO.getCompanyId()"),
//            @CachePut(value = "id", key = "#blacklistDTO.getId()")
//
//    })
    @Transactional
    public BlacklistResp save(BlacklistResp blacklistDTO) {

        log.info("Adding to blacklist company with id = {} ", blacklistDTO.getCompanyId());

        List<Blacklist> duplicate =
                blacklistRepository
                        .findBlacklistByCompanyId(
                                blacklistDTO.getCompanyId());

        duplicate.forEach(it -> {
                    if (Objects.nonNull(it.getDeletionDate())) {
                        throw new ServiceException(HttpStatus.CONFLICT.value(), "exception.company.duplicate_company_in_blacklist");
                    }
                }
        );

        Blacklist blacklist = blacklistRepository.save(
                blacklistMapperDTO.toEntity(blacklistDTO));

        return blacklistMapperDTO.toDto(blacklist);
    }

    @Override
    public PageWrapper<BlacklistResp> findAll(Integer page, Integer size) {
        log.info("Received a request to find all companies in black list");

        Specification<Blacklist> specification =
                Specification.where(BlacklistSpecifications
                        .notDeleted());

        Pageable pageable =
                PageRequest.of(page, size);

        Page<Blacklist> blacklistPage =
                blacklistRepository.findAll(specification, pageable);


        return
                new PageWrapper<>(blacklistMapperDTO
                .toDtoList(blacklistPage.toList()),
                blacklistPage.getTotalPages(),
                blacklistPage.getTotalElements());
    }


    @Override
    public BlacklistResp findById(Long id) {
        log.info("Finding blacklist information with id = {} ", id);

        Blacklist blacklist = blacklistRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("blacklist information with id = " + id + " not found "));

        return blacklistMapperDTO
                .toDto(blacklist);
    }

    @Override
//    @Caching(evict = {
//            @CacheEvict(value = "company_id", key = "#result"),
//            @CacheEvict(value = "id", key = "#id")
//    })
    @Transactional
    public Long delete(BlacklistDeleteResp blacklistDeleteDTO, Long id) {
        log.info("Received request to delete company from blacklist with id = {}", id);

        Blacklist blacklist = blacklistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("blacklist information with id = " + id + " not found "));

        blacklist.setDeletionDate(LocalDateTime.now());

        blacklist.setDeletionReason(blacklistDeleteDTO.getDeletionReason());

        blacklistRepository.save(blacklist);

        return blacklist.getId();
    }

    @Override
    public PageWrapper<BlacklistResp> findAllByCompanyId(Integer page, Integer size, Long companyId) {
        log.info("Received a request to find all information about company with id ={} in black list", companyId);

        Pageable pageable =
                PageRequest.of(page, size);

        Page<Blacklist> blacklistPage =
                blacklistRepository.findAllByCompanyId(companyId, pageable);


        return
                new PageWrapper<>(blacklistMapperDTO
                .toDtoList(blacklistPage.toList()),
                blacklistPage.getTotalPages(),
                blacklistPage.getTotalElements());
    }
}
