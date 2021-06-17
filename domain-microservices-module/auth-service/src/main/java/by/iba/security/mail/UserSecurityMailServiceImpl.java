package by.iba.security.mail;

import by.iba.security.mail.exception.InvalidEmailException;
import by.iba.configuration.BaseEmailProperties;
import by.iba.exception.EmailServiceException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import by.iba.core.EmailSenderService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Service
@AllArgsConstructor
@Slf4j
public class UserSecurityMailServiceImpl implements UserSecurityMailService {

    private final EmailSenderService emailSenderService;
    private final BaseEmailProperties baseEmailProperties;

    @Override
    public void sendConfirmationEmail(String recipient, String confirmationToken) {
        log.info("Sending message started");

        final MimeMessage message = getConfirmAccountMessage(recipient, confirmationToken);

        try {
            this.emailSenderService.send(message);
            log.info("Confirm message sent successfully");
        } catch (EmailServiceException e) {
            log.error("Wrong email" + e.getMessage());
            throw new InvalidEmailException("No such email");
        }
    }

    private MimeMessage getConfirmAccountMessage(final String recipient,
                                                 final String confirmationToken) {

        log.info("Sending to: {} with token = {} and send from {}", recipient, confirmationToken,
                baseEmailProperties.getEmailSender());

        return getMimeMessage(recipient, confirmationToken);
    }


    private MimeMessage getMimeMessage(String recipient, String token) {
        final Properties properties = getPortAndHostProperties();
        final Session session = Session.getDefaultInstance(properties, null);

        try {

            InternetAddress iaSender = new InternetAddress(baseEmailProperties.getEmailSender());
            InternetAddress iaRecipient = new InternetAddress(recipient);
            InternetHeaders headers = new InternetHeaders();
            headers.addHeader("Content-type", "text/html; charset=UTF-8");


            String html = "\n<a href=http://localhost:6000/accounts/confirm/" + token + ">click here</a>";

            MimeBodyPart body = new MimeBodyPart(headers, html.getBytes(StandardCharsets.UTF_8));
            body.setText(html, "UTF-8", "html");

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(body);

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSender(iaSender);
            mimeMessage.setSubject("Finish registration");
            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
            mimeMessage.setContent(multipart);


            return mimeMessage;
        } catch (MessagingException e) {
            log.error("messaging exception :" + e.getMessage());
            throw new EmailServiceException("Can`t send email");
            //e.printStackTrace();
        }
    }

    private Properties getPortAndHostProperties() {
        log.info("Get properties ");

        final Properties properties = new Properties();
        properties.put("mail.smtp.host", baseEmailProperties.getSmtpHost());
        properties.put("mail.smtp.port", baseEmailProperties.getSmtpPort());

        properties.put("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtps.auth", "true");

        return properties;
    }
}
