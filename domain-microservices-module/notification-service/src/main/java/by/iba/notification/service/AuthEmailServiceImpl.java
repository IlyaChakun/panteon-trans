package by.iba.notification.service;

import by.iba.notification.email.mailchimp.MailChimpClient;
import by.iba.notification.email.mailchimp.MailChimpTemplateConstants;
import by.iba.notification.email.service.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
@Slf4j
public class AuthEmailServiceImpl implements AuthEmailService {

    private final MailChimpClient mailChimpClient;
    private MailService mailService;

    @Override
    public void welcomeMessage(String email, String name) {

        String subject = "You Redeemed Your Gatsby Rewards!";
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("firstname", "");

        String template = MailChimpTemplateConstants.Name;//any anmae
        mailChimpClient.sendToMailChimp(template, subject, email, parameters);

//        log.create("rewardsGetPaidOut")
//                .field(USER_ID, userEntity.getId())
//                .field(USERNAME, userEntity.getEmail())
//                .field(USER_EMAIL, userEntity.getEmail())
//                .warn(template + ", email: " + userEntity.getEmail());
    }

    @Override
    public void sendSuccessMessage(String email) {
        

        mailService.sendSuccessRegistrationMessage(email);
    }
}
