package by.iba.company.companies.controler;

import by.iba.common.dto.PageWrapper;
import by.iba.common.validation.annotation.PositiveLong;
import by.iba.company.companies.dto.CompanyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/companies")
public interface CompanyController {

    @PostMapping
    ResponseEntity<CompanyDTO> save(@Valid @RequestBody final CompanyDTO companyDTO,
                                    final BindingResult bindingResult);

    @PutMapping("/{id}")
    ResponseEntity<CompanyDTO> update(@PathVariable("id") @PositiveLong final Long id,
                                      @RequestBody @Valid final CompanyDTO companyDTO,
                                      final BindingResult bindingResult);

    @DeleteMapping("/{unp}")
    ResponseEntity<Void> delete(@PathVariable("unp") final String unp);

    @GetMapping("/unp/{unp}")
    ResponseEntity<CompanyDTO> findByUNP(@PathVariable("unp") final String unp);

    @GetMapping("/{id}")
    ResponseEntity<CompanyDTO> findById(@PathVariable("id") final Long id);

    @GetMapping
    ResponseEntity<PageWrapper<CompanyDTO>> findAll(@RequestParam(defaultValue = "0", required = false) final Integer page,
                                                    @RequestParam(defaultValue = "10", required = false) final Integer size);

}