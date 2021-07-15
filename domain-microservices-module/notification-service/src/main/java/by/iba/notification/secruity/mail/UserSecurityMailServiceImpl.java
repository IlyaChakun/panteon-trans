//package by.iba.notification.secruity.mail;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
////import by.iba.email.core.EmailSenderService;
//
////import javax.mail.Message;
////import javax.mail.MessagingException;
////import javax.mail.Session;
////import javax.mail.internet.*;
//
//
//@Service
//@AllArgsConstructor
//@Slf4j
//public class UserSecurityMailServiceImpl {//implements UserSecurityMailService {
///*
//    private final EmailSenderService emailSenderService;
//    private final BaseEmailProperties baseEmailProperties;
//
//    @Override
//    public void sendConfirmationEmail(String recipient, String confirmationToken) {
//
//
//        final MimeMessage message = getConfirmAccountMessage(recipient, confirmationToken);
//
//        sendMimeMessage(message);
//    }
//
//    @Override
//    public void sendPasswordRecoveryMessage(String recipient, String confirmationToken) {
//
//
//        final MimeMessage message = getPasswordRecoveryMessage(recipient, confirmationToken);
//
//        sendMimeMessage(message);
//
//    }
//
//    @Override
//    public void sendSuccessPasswordUpdateMessage(String recipient) {
//
//
//        final SimpleMailMessage message = getSuccessEmail(recipient,"Password was successfully updated ");
//
//        sendSimpleMailMessage(message);
//
//    }
//
//    @Override
//    public void sendSuccessRegistrationMessage(String recipient) {
//
//
//        final SimpleMailMessage message = getSuccessEmail(recipient,"Registration finished!");
//
//        sendSimpleMailMessage(message);
//
//    }
//
//    private void sendMimeMessage(MimeMessage message) {
//
//        try {
//            this.emailSenderService.send(message);
//
//        } catch (EmailServiceException e) {
//            log.error("Wrong email" + e.getMessage());
//            throw new InvalidEmailException("No such email");
//        }
//
//    }
//
//    private void sendSimpleMailMessage(SimpleMailMessage message) {
//        try {
//            this.emailSenderService.send(message);
//
//        } catch (EmailServiceException e) {
//            log.error("Wrong email" + e.getMessage());
//            throw new InvalidEmailException("No such email");
//        }
//    }
//
//    private MimeMessage getConfirmAccountMessage(final String recipient,
//                                                 final String confirmationToken) {
//
//
//                baseEmailProperties.getEmailSender());
//        String html = "\n<a href=http://localhost:6000/accounts/confirm/" + confirmationToken + ">click here</a>";
//
//        return getMimeMessage(recipient, "Confirm your email and activate Account", html);
//    }
//
//    private MimeMessage getPasswordRecoveryMessage(final String recipient,
//                                                   final String confirmationToken) {
//
//
//                recipient,
//                confirmationToken,
//                baseEmailProperties.getEmailSender());
//
//        String html = "\n<a href=http://localhost:6000/accounts/password-recovery/" + confirmationToken + ">recover password</a>";
//
//        return getMimeMessage(recipient, "Password recovery", html);
//    }
//
//
//    private MimeMessage getMimeMessage(String recipient,
//                                       String subject,
//                                       String html) {
//
//        final Properties properties = getPortAndHostProperties();
//        final Session session = Session.getDefaultInstance(properties, null);
//
//        try {
//
//            InternetAddress iaSender = new InternetAddress(baseEmailProperties.getEmailSender());
//            InternetAddress iaRecipient = new InternetAddress(recipient);
//            InternetHeaders headers = new InternetHeaders();
//            headers.addHeader("Content-type", "text/html; charset=UTF-8");
//
//
//            MimeBodyPart body = new MimeBodyPart(headers, html.getBytes(StandardCharsets.UTF_8));
//            body.setText(html, "UTF-8", "html");
//
//            MimeMultipart multipart = new MimeMultipart();
//            multipart.addBodyPart(body);
//
//            MimeMessage mimeMessage = new MimeMessage(session);
//            mimeMessage.setSender(iaSender);
//            mimeMessage.setSubject(subject);
//            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
//            mimeMessage.setContent(multipart);
//
//
//            return mimeMessage;
//        } catch (MessagingException e) {
//            log.error("messaging exception :" + e.getMessage());
//            throw new EmailServiceException("Can`t send email");
//        }
//    }
//
//    private SimpleMailMessage getSuccessEmail(final String recipient,String text) {
//
//
//
//
//
//        final String subject = "Finish registration";
//        final SimpleMailMessage message = new SimpleMailMessage();
//        message.setSubject(subject);
//        message.setTo(recipient);
//        message.setFrom(baseEmailProperties.getEmailSender());
//        message.setText(text);
//        return message;
//    }
//
//    private Properties getPortAndHostProperties() {
//
//
//        final Properties properties = new Properties();
//        properties.put("mail.smtp.host", baseEmailProperties.getSmtpHost());
//        properties.put("mail.smtp.port", baseEmailProperties.getSmtpPort());
//
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.setProperty("mail.smtps.auth", "true");
//
//        return properties;
//    }*/
//}
