package by.iba.blacklist.service;

import by.iba.blacklist.domain.Blacklist;
import by.iba.blacklist.dto.BlacklistDTO;
import by.iba.blacklist.dto.mapper.BlacklistMapperDTO;
import by.iba.blacklist.repository.BlacklistRepository;
import by.iba.blacklist.scpecifications.BlacklistSpecifications;
import by.iba.common.dto.PageWrapper;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.common.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Slf4j
@AllArgsConstructor
public class BlacklistServiceImpl implements BlacklistService {

    private final BlacklistRepository blacklistRepository;
    private final BlacklistMapperDTO blacklistMapperDTO;

    @Override
    @Caching(put = {
            @CachePut(value = "company_id", key = "#blacklistDTO.getCompanyId()"),
            @CachePut(value = "id", key = "#blacklistDTO.getId()")

    })
    @Transactional
    public BlacklistDTO save(BlacklistDTO blacklistDTO) {

        log.info("Adding to blacklist company with id = {} ", blacklistDTO.getCompanyId());

        Optional<Blacklist> duplicate =
                blacklistRepository
                        .findBlacklistByCompanyId(
                                blacklistDTO.getCompanyId());

        if (duplicate.isPresent() &&
                Objects.isNull(duplicate.get().getDeletionDate()))
            throw new ServiceException(HttpStatus.CONFLICT.value(), "exception.company.duplicate_company_in_blacklist");

        Blacklist blacklist = blacklistMapperDTO
                .toEntity(blacklistDTO);

        blacklistRepository
                .save(blacklist);

        return blacklistMapperDTO
                .toDto(blacklist);
    }

    @Override
    public PageWrapper<BlacklistDTO> findAll(Integer page, Integer size) {
        log.info("Received a request to find all companies in black list");

        Specification<Blacklist> specification =
                Specification.where(BlacklistSpecifications
                        .notDeleted());

        Pageable pageable =
                PageRequest.of(page, size);

        Page<Blacklist> blacklistPage =
                blacklistRepository.findAll(specification, pageable);


        return new PageWrapper<>(blacklistMapperDTO
                .toDtoList(blacklistPage.toList()),
                blacklistPage.getTotalPages(),
                blacklistPage.getTotalElements());
    }


    @Override
    public BlacklistDTO findById(Long id) {
        log.info("Finding blacklist information with id = {} ", id);

        Blacklist blacklist = blacklistRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("blacklist information with id = " + id + " not found "));

        return blacklistMapperDTO
                .toDto(blacklist);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "company_id", key = "#blacklistDTO.getCompanyId()"),
            @CacheEvict(value = "id", key = "#result")

    })
    public Long delete(BlacklistDTO blacklistDTO) {
        log.info("Received request to delete company from blacklist with id = {}", blacklistDTO.getId());

        Blacklist blacklist = blacklistMapperDTO.toEntity(blacklistDTO);

        blacklistRepository
                .findById(blacklist.getId())
                .orElseThrow(() -> new ResourceNotFoundException("blacklist information with id = " + blacklist.getId() + " not found "));

        blacklist.setDeletionDate(LocalDateTime.now());
        blacklistRepository.save(blacklist);

        return blacklist.getId();
    }

    @Override
    public PageWrapper<BlacklistDTO> findAllByCompanyId(Integer page, Integer size, Long companyId) {
        log.info("Received a request to find all information about company with id ={} in black list", companyId);

        Pageable pageable =
                PageRequest.of(page, size);

        Page<Blacklist> blacklistPage =
                blacklistRepository.findAllByCompanyId(companyId, pageable);


        return new PageWrapper<>(blacklistMapperDTO
                .toDtoList(blacklistPage.toList()),
                blacklistPage.getTotalPages(),
                blacklistPage.getTotalElements());
    }
}
