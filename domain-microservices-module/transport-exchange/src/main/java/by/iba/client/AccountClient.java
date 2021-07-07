package by.iba.client;

import by.iba.common.dto.PatchReq;
import by.iba.common.exception.ServiceException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "account-service")
public interface AccountClient {

    @PostMapping("/accounts")
    void patch(PatchReq patchReq) throws ServiceException;

}
