package by.iba.company.blacklist.controller;

import by.iba.company.blacklist.dto.BlacklistResp;
import by.iba.company.blacklist.dto.BlacklistDeleteResp;
import by.iba.common.dto.PageWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/blacklist")
public interface BlacklistController {

    @GetMapping("/{id}")
    ResponseEntity<BlacklistResp> findById(@PathVariable("id") final Long id);

    @GetMapping()
    ResponseEntity<PageWrapper<BlacklistResp>> findAll(@RequestParam(defaultValue = "0", required = false) final Integer page,
                                                       @RequestParam(defaultValue = "10", required = false) final Integer size);

    @PostMapping()
    ResponseEntity<BlacklistResp> save(@RequestBody BlacklistResp blacklistDTO);

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(BlacklistDeleteResp blacklistDeleteDTO, @PathVariable("id") final Long id);

    @GetMapping("/companies/{companyId}/history")
    ResponseEntity<PageWrapper<BlacklistResp>> findAllByCompanyId(@RequestParam(defaultValue = "0", required = false) final Integer page,
                                                                  @RequestParam(defaultValue = "10", required = false) final Integer size,
                                                                  @PathVariable("companyId") final Long companyId);

}
