package by.iba.company.blacklist.controller;

import by.iba.company.blacklist.dto.BlacklistResp;
import by.iba.company.blacklist.dto.BlacklistDeleteResp;
import by.iba.company.blacklist.service.BlacklistService;
import by.iba.common.dto.PageWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@AllArgsConstructor
@RestController
public class BlacklistControllerImpl implements BlacklistController {

    private final BlacklistService blacklistService;

    @Override
    public ResponseEntity<BlacklistResp> findById(Long id) {
        log.info("Finding company in blacklist by id = {}", id);

        BlacklistResp blacklistDTO = blacklistService.findById(id);

        return ResponseEntity
                .ok()
                .body(blacklistDTO);
    }

    @Override
    public ResponseEntity<PageWrapper<BlacklistResp>> findAll(Integer page, Integer size) {
        log.info("Finding all companies in blacklist");

        PageWrapper<BlacklistResp> blacklistDTOS = blacklistService
                .findAll(page, size);

        return ResponseEntity
                .ok()
                .body(blacklistDTOS);
    }

    @Override
    public ResponseEntity<BlacklistResp> save(BlacklistResp blacklistDTO) {
        log.info("Received a request to add to blacklist company with id = {}", blacklistDTO.getCompanyId());

        BlacklistResp savedInfo = blacklistService.save(blacklistDTO);


        final URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/blacklist")
                .buildAndExpand(blacklistDTO.getCompanyId(), savedInfo.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(savedInfo);
    }

    public ResponseEntity<Void> delete(BlacklistDeleteResp blacklistDeleteDTO, Long id) {
        log.info("Received a request to delete blacklist info with id = {}", id);

        blacklistService.delete(blacklistDeleteDTO, id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @Override
    public ResponseEntity<PageWrapper<BlacklistResp>> findAllByCompanyId(Integer page, Integer size, Long companyId) {
        log.info("Finding all information about company with id = {} in blacklist", companyId);

        PageWrapper<BlacklistResp> blacklistDTOS = blacklistService
                .findAllByCompanyId(page, size, companyId);

        return ResponseEntity
                .ok()
                .body(blacklistDTOS);
    }

}
