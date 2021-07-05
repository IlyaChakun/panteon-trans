package by.iba.client;

import by.iba.common.exception.ServiceException;
import by.iba.dto.CompanyResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "transport-exchange-service")
public interface CompanyServiceClient {

    @PostMapping("/companies")
    ResponseEntity<CompanyResp> save(CompanyResp company) throws ServiceException;

    @GetMapping("/companies/{id}")
    ResponseEntity<CompanyResp> findById(@PathVariable(name = "id") Long companyId) throws ServiceException;
}
