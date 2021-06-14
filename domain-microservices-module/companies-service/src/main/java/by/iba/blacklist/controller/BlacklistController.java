package by.iba.blacklist.controller;

import by.iba.blacklist.dto.BlacklistDTO;
import by.iba.blacklist.dto.BlacklistDeleteDTO;
import by.iba.common.dto.PageWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/blacklist")
public interface BlacklistController {

    @GetMapping("/{id}")
    ResponseEntity<BlacklistDTO> findById(@PathVariable("id") final Long id);

    @GetMapping()
    ResponseEntity<PageWrapper<BlacklistDTO>> findAll(@RequestParam(defaultValue = "0", required = false) final Integer page,
                                                      @RequestParam(defaultValue = "10", required = false) final Integer size);

    @PostMapping()
    ResponseEntity<BlacklistDTO> save(BlacklistDTO blacklistDTO);

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(BlacklistDeleteDTO blacklistDeleteDTO, @PathVariable("id") final Long id);

    @GetMapping("/companies/{companyId}/history")
    ResponseEntity<PageWrapper<BlacklistDTO>> findAllByCompanyId(@RequestParam(defaultValue = "0", required = false) final Integer page,
                                                                 @RequestParam(defaultValue = "10", required = false) final Integer size,
                                                                 @PathVariable("companyId") final Long companyId);

}
