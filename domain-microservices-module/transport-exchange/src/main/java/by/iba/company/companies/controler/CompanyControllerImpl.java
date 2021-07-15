package by.iba.company.companies.controler;

import by.iba.common.controller.ControllerHelper;
import by.iba.common.dto.PageWrapper;
import by.iba.company.companies.dto.CompanyCriteria;
import by.iba.company.companies.dto.CompanyReq;
import by.iba.company.companies.dto.CompanyResp;
import by.iba.company.companies.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@AllArgsConstructor
@Slf4j
public class CompanyControllerImpl implements CompanyController {

    private final CompanyService companyService;

    @Override
    public ResponseEntity<CompanyResp> save(@Valid final CompanyReq companyReq,
                                            final BindingResult bindingResult) {

        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        final CompanyResp savedCompany = companyService.save(companyReq);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/companies/{unp}")
                .buildAndExpand(savedCompany.getUnp())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(savedCompany);
    }

    @Override
    public ResponseEntity<Void> delete(final String unp) {
        companyService.deleteByUnp(unp);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CompanyResp> findByUNP(final String unp) {

        final CompanyResp companyResp = companyService.findByUnp(unp);

        return ResponseEntity
                .ok()
                .body(companyResp);
    }

    @Override
    public ResponseEntity<CompanyResp> findById(final Long id) {

        final CompanyResp companyResp = companyService.findById(id);

        return ResponseEntity
                .ok()
                .body(companyResp);
    }

    @Override
    public ResponseEntity<PageWrapper<CompanyResp>> findAll(CompanyCriteria companyCriteria) {
        

        final PageWrapper<CompanyResp> allCompanies = companyService.findAll(companyCriteria);

        return ResponseEntity
                .ok()
                .body(allCompanies);
    }
}
