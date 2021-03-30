package by.iba.client;

import by.iba.common.exception.ServiceException;
import by.iba.dto.CompanyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "companies-service")
public interface CompanyServiceClient {

    @PostMapping("/companies")
    ResponseEntity<CompanyDTO> save(CompanyDTO company) throws ServiceException;

}
