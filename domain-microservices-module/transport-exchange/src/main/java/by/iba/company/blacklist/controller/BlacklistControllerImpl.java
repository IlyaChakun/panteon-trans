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

        BlacklistResp blacklistDTO = blacklistService.findById(id);

        return ResponseEntity
                .ok()
                .body(blacklistDTO);
    }

    @Override
    public ResponseEntity<PageWrapper<BlacklistResp>> findAll(Integer page, Integer size) {

        PageWrapper<BlacklistResp> blacklistDTOS = blacklistService
                .findAll(page, size);

        return ResponseEntity
                .ok()
                .body(blacklistDTOS);
    }

    @Override
    public ResponseEntity<BlacklistResp> save(BlacklistResp blacklistDTO) {

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

        blacklistService.delete(blacklistDeleteDTO, id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @Override
    public ResponseEntity<PageWrapper<BlacklistResp>> findAllByCompanyId(Integer page, Integer size, Long companyId) {

        PageWrapper<BlacklistResp> blacklistDTOS = blacklistService
                .findAllByCompanyId(page, size, companyId);

        return ResponseEntity
                .ok()
                .body(blacklistDTOS);
    }

}
