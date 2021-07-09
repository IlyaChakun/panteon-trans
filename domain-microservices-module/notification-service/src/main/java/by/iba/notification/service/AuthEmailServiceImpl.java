package by.iba.notification.service;

import by.iba.notification.email.mailchimp.MailChimpClient;
import by.iba.notification.email.mailchimp.MailChimpTemplateConstants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class AuthEmailServiceImpl implements AuthEmailService {

    private final MailChimpClient mailChimpClient;

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
}
