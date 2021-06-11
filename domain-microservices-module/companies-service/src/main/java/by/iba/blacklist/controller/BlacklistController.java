package by.iba.blacklist.controller;

import by.iba.blacklist.dto.BlacklistDTO;
import by.iba.common.dto.PageWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/companies/blackList")
public interface BlacklistController {

    @GetMapping("/{id}")
    ResponseEntity<BlacklistDTO> findById(@PathVariable("id") final Long id);

    @GetMapping()
    ResponseEntity<PageWrapper<BlacklistDTO>> findAll(@RequestParam(defaultValue = "0", required = false) final Integer page,
                                                      @RequestParam(defaultValue = "10", required = false) final Integer size);

    @PostMapping("{companyId}")
    ResponseEntity<BlacklistDTO> addCompanyToBlacklist(BlacklistDTO blacklistDTO, @PathVariable("companyId") final Long id);

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable("id") final Long id);
}
