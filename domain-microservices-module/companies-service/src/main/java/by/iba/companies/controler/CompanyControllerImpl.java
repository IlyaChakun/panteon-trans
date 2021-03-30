package by.iba.companies.controler;

import by.iba.common.controller.ControllerHelper;
import by.iba.common.dto.PageWrapper;
import by.iba.companies.dto.CompanyDTO;
import by.iba.companies.service.CompanyService;
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
    public ResponseEntity<CompanyDTO> save(@Valid final CompanyDTO companyDTO,
                                           final BindingResult bindingResult) {

        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);//TODO  это для проверки валидации (успешно не успешно )

        log.info("Received a request to save the company with unp = {}", companyDTO.getUNP());

        final CompanyDTO savedCompany = companyService.save(companyDTO);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/companies/{unp}")
                .buildAndExpand(savedCompany.getUNP())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(savedCompany);
    }

    @Override
    public ResponseEntity<CompanyDTO> update(final Long companyId, final @Valid CompanyDTO companyDTO,
                                             final BindingResult bindingResult) {
        log.info("Received a request to update the company with id = {}", companyDTO);

        final CompanyDTO updatedCompany = companyService.update(companyId, companyDTO);

        return ResponseEntity
                .ok()
                .body(updatedCompany);
    }

    @Override
    public ResponseEntity<Void> delete(final String unp) {
        log.info("Received a request to delete the company with unp = {}", unp);

        companyService.delete(unp);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CompanyDTO> findByUNP(final String unp) {
        log.info("Received a request to find the company with unp = {}", unp);

        final CompanyDTO companyDTO = companyService.findByUNP(unp);

        return ResponseEntity
                .ok()
                .body(companyDTO);
    }

    @Override
    public ResponseEntity<CompanyDTO> findById(final Long id) {
        log.info("Received a request to find the company by id = {}", id);

        final CompanyDTO companyDTO = companyService.findById(id);

        return ResponseEntity
                .ok()
                .body(companyDTO);
    }

    @Override
    public ResponseEntity<PageWrapper<CompanyDTO>> findAll(final Integer page, final Integer size) {
        log.info("Received a request to find all companies");

        final PageWrapper<CompanyDTO> allCompanies = companyService.findAll(page, size);

        return ResponseEntity
                .ok()
                .body(allCompanies);
    }
}
