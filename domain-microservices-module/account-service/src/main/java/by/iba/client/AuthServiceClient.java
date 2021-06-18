package by.iba.client;

import by.iba.common.dto.ApiResponse;
import by.iba.common.exception.ServiceException;
import by.iba.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "auth-service")
public interface AuthServiceClient {

    @PostMapping("/users")
    ResponseEntity<UserDTO> save(UserDTO user) throws ServiceException;

    @PostMapping(value = "/users/confirm-account/{token}")
    ResponseEntity<ApiResponse> confirmUserAccount(@PathVariable("token")String token);

}
