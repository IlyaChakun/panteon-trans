package by.iba.core;

import by.iba.exception.EmailServiceException;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;


public interface EmailSenderService {

    void send(SimpleMailMessage message) throws EmailServiceException;

    void send(MimeMessage message) throws EmailServiceException;
}
