package by.iba.company.companies.controler;

import by.iba.common.dto.PageWrapper;
import by.iba.company.companies.dto.CompanyCriteria;
import by.iba.company.companies.dto.CompanyReq;
import by.iba.company.companies.dto.CompanyResp;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/companies")
public interface CompanyController {

    @PostMapping
    ResponseEntity<CompanyResp> save(@Valid @RequestBody final CompanyReq companyReq,
                                     final BindingResult bindingResult);

    @DeleteMapping("/{unp}")
    ResponseEntity<Void> delete(@PathVariable("unp") final String unp);

    @GetMapping("/unp/{unp}")
    ResponseEntity<CompanyResp> findByUNP(@PathVariable("unp") final String unp);

    @GetMapping("/{id}")
    ResponseEntity<CompanyResp> findById(@PathVariable("id") final Long id);

    @GetMapping
    ResponseEntity<PageWrapper<CompanyResp>> findAll(CompanyCriteria companyCriteria);

}