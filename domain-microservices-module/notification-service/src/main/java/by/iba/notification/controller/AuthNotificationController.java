package by.iba.notification.controller;

import by.iba.notification.domain.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/notification")
@CrossOrigin(origins = "*")
public interface AuthNotificationController {

    @PreAuthorize("#oauth2.hasScope('server')")
    @GetMapping("/success-registration/{email}")
    ResponseEntity<ApiResponse> sendSuccessRegistrationMessage(@PathVariable String email);


}
