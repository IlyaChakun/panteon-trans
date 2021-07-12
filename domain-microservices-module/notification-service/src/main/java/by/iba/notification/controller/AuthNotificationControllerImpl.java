package by.iba.notification.controller;


import by.iba.notification.domain.ApiResponse;
import by.iba.notification.service.AuthEmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
public class AuthNotificationControllerImpl implements AuthNotificationController {

    private final AuthEmailService emailService;

    @Override
    public ResponseEntity<ApiResponse> sendSuccessRegistrationMessage(String email) {
        log.info("Received a request to send success registration message to {}", email);

        emailService.sendSuccessMessage(email);

        return ResponseEntity.ok(
                new ApiResponse(true, "Success message sent successfully")
        );
    }
}
