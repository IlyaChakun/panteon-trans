package by.iba.core;

import by.iba.exception.EmailServiceException;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Objects;

@Service
@AllArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender javaMailSender;

    @Override
    @Async
    public void send(final SimpleMailMessage message) {
        if (Objects.nonNull(message)) {
            try {

                this.javaMailSender.send(message);
            } catch (Exception e) {

                throw new EmailServiceException("Can't send email", e);
            }
        }
    }

    @Override
    @Async
    public void send(final MimeMessage message) {
        if (Objects.nonNull(message)) {
            try {

                this.javaMailSender.send(message);
            } catch (Exception e) {

                throw new EmailServiceException("Can't send email", e);
            }
        }
    }
}
