package by.iba.notification.email.core;

import by.iba.notification.email.exception.EmailServiceException;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;


public interface EmailSenderService {

    void send(SimpleMailMessage message) throws EmailServiceException;

    void send(MimeMessage message) throws EmailServiceException;
}
