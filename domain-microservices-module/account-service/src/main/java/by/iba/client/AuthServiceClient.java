package by.iba.client;

import by.iba.common.exception.ServiceException;
import by.iba.dto.CompanyDTO;
import by.iba.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "auth-service")
public interface AuthServiceClient {

    @PostMapping("/users")
    ResponseEntity<UserDTO> save(UserDTO user) throws ServiceException;

    @PostMapping("/companies")
    ResponseEntity<CompanyDTO> saveCompany(CompanyDTO company) throws ServiceException;

}
