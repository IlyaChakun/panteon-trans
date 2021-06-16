package by.iba.security.mail;

import by.iba.security.mail.exception.InvalidEmailException;
import by.iba.configuration.BaseEmailProperties;
import by.iba.exception.EmailServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import by.iba.core.EmailSenderService;

@Service
@AllArgsConstructor
@Slf4j
public class UserSecurityMailServiceImpl implements UserSecurityMailService {

    private final EmailSenderService emailSenderService;
    private final BaseEmailProperties baseEmailProperties;

    @Override
    public void sendConfirmationEmail(String recipient, String confirmationToken) {

        final SimpleMailMessage message = getConfirmAccountMessage(recipient, confirmationToken);

        try {
            this.emailSenderService.send(message);
            log.info("Confirm message sent successfully");
        } catch (EmailServiceException e) {
            log.error("Wrong email" + e.getMessage());
            throw new InvalidEmailException("No such email");
        }
    }

    private SimpleMailMessage getConfirmAccountMessage(final String recipient,
                                                       final String confirmationToken) {

        log.info("Sending to: {} with token = {}", recipient, confirmationToken);

        log.info("Send from: {}", baseEmailProperties.getEmailSender());

        final String subject = "Finish registration";

        final SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject(subject);
        message.setTo(recipient);
        message.setFrom(baseEmailProperties.getEmailSender());
        message.setText("To finish your registration, use this link : "
                + "http://localhost:5000/users/confirm-account?token=" + confirmationToken);

        return message;
    }
}
