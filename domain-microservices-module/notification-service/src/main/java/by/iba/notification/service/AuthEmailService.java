package by.iba.notification.service;

public interface AuthEmailService {
    void welcomeMessage(String email, String name);

    void sendSuccessMessage(String email);
}
