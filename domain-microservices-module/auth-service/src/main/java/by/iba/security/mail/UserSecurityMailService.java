package by.iba.security.mail;

public interface UserSecurityMailService {

    void sendConfirmationEmail(final String recipient, final String confirmationToken);
}
