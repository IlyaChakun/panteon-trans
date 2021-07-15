package by.iba.client;

import by.iba.common.dto.ApiResponse;
import by.iba.common.dto.PatchReq;
import by.iba.common.exception.ServiceException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "account-service")
public interface AccountClient {

    @PostMapping("/accounts")
    void patch(PatchReq patchReq) throws ServiceException;

    @PostMapping("/accounts/{id}/add-company/{companyId}")
    ResponseEntity<ApiResponse> addCompanyToAccount(@PathVariable Long id,
                                                    @PathVariable Long companyId);

}
