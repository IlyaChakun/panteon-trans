package by.iba.companies.controler;

import by.iba.common.dto.PageWrapper;
import by.iba.companies.dto.CompanyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/companies")
public interface CompanyController {

    @PostMapping
    ResponseEntity<CompanyDTO> save(@Valid @RequestBody final CompanyDTO companyDTO,
                                    final BindingResult bindingResult);

    @PutMapping("/{unp}")
    ResponseEntity<CompanyDTO> update(@PathVariable("unp") final String unp,
                                      @RequestBody @Valid final CompanyDTO companyDTO,
                                      final BindingResult bindingResult);

    @DeleteMapping("/{unp}")
    ResponseEntity<Void> delete(@PathVariable("unp") final String unp);

    @GetMapping("/{unp}")
    ResponseEntity<CompanyDTO> findByUNP(@PathVariable("unp") final String unp);

    @GetMapping
    ResponseEntity<PageWrapper<CompanyDTO>> findAll();

}
