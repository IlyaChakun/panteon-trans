package by.iba.blacklist.controller;

import by.iba.blacklist.dto.BlacklistDTO;
import by.iba.blacklist.service.BlacklistService;
import by.iba.common.dto.PageWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class BlacklistControllerImpl implements BlacklistController {

    private final BlacklistService blacklistService;

    @Override
    public ResponseEntity<BlacklistDTO> findById(Long id) {
        log.info("Finding company in blacklist by id = {}", id);

        BlacklistDTO blacklistDTO = blacklistService.findById(id);

        return ResponseEntity
                .ok()
                .body(blacklistDTO);
    }

    @Override
    public ResponseEntity<PageWrapper<BlacklistDTO>> findAll(Integer page, Integer size) {
        log.info("Finding all companies in blacklist");

        PageWrapper<BlacklistDTO> blacklistDTOS = blacklistService
                .findAll(page, size);

        return ResponseEntity
                .ok()
                .body(blacklistDTOS);
    }

    @Override
    public ResponseEntity<BlacklistDTO> addCompanyToBlacklist(BlacklistDTO blacklistDTO, Long id) {
        log.info("Received a request to add to blacklist company with id = {}", id);

        BlacklistDTO savedInfo = blacklistService.save(blacklistDTO);


        final URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/companies/blacklist/{id}")
                .buildAndExpand(id, savedInfo.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(savedInfo);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        log.info("Received a request to delete blacklist info with id = {}", id);

        blacklistService.deleteById(id);

        return ResponseEntity
                .noContent()
                .build();
    }

}
