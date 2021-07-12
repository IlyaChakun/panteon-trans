package by.iba.client;

import by.iba.common.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "notification-service")
public interface NotificationClient {

    @GetMapping("/notification/success-registration/{email}")
    ResponseEntity<ApiResponse> sendSuccessRegistrationMessage(@PathVariable String email);
}
