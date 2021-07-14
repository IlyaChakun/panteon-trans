package by.iba.notification.controller;

import by.iba.notification.domain.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/notification")
@CrossOrigin(origins = "*")
public interface AuthNotificationController {

    @GetMapping("/success-registration/{email}")
    ResponseEntity<ApiResponse> sendSuccessRegistrationMessage(@PathVariable String email);


}
