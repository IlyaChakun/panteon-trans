package by.iba.notification.email.service;

public interface MailService {

    void sendConfirmationEmail(final String recipient, final String confirmationToken);

    void sendPasswordRecoveryMessage(final String recipient, final String confirmationToken);

    void sendSuccessPasswordUpdateMessage(final String recipient);

    void sendSuccessRegistrationMessage(final String recipient);
}
