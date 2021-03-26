package by.iba.companies.controler;

import by.iba.common.dto.PageWrapper;
import by.iba.common.validation.annotation.PositiveLong;
import by.iba.companies.dto.CompanyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/companies")
public interface CompanyController {

    @PostMapping
    ResponseEntity<CompanyDTO> save(@Valid @RequestBody CompanyDTO companyDTO);

    @PutMapping("/{id}")
    ResponseEntity<CompanyDTO> update(@PathVariable("id") @PositiveLong String companyId,
                                    @RequestBody @Valid CompanyDTO companyDTO,
                                    BindingResult bindingResult);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") @PositiveLong String companyId);

    @GetMapping("/{id}")
    ResponseEntity<CompanyDTO> findById(@PathVariable("id") @PositiveLong String companyId);

    @GetMapping
    ResponseEntity<PageWrapper<CompanyDTO>> findAll();

}
