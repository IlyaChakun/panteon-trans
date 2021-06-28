package by.iba.cargo.mail;

import by.iba.cargo.mail.exception.EmailException;
import by.iba.configuration.BaseEmailProperties;
import by.iba.core.EmailSenderService;
import by.iba.exception.EmailServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


@Service
@AllArgsConstructor
@Slf4j
public class CargoMailServiceImpl implements CargoMailService {

    private final EmailSenderService emailSenderService;
    private final BaseEmailProperties baseEmailProperties;

    @Override
    public void sendSaveCargoNotification(String recipient) {
        log.info("Sending message to {} to confirm email", recipient);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setFrom(baseEmailProperties.emailSender);
        simpleMailMessage.setSubject("Finished saved cargo");
        simpleMailMessage.setText("Your cargo is saved in the database!");

        try {
            this.emailSenderService.send(simpleMailMessage);
            log.info("Confirm message sent successfully");
        } catch (EmailServiceException e) {
            log.error("Wrong email" + e.getMessage());
            throw new EmailException("No such email");
        }
    }
}