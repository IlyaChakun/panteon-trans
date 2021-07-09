package by.iba.notification.email.mailchimp;

import java.util.HashMap;

public interface MailChimpClient {

    boolean sendToMailChimp(String templateSlug, String subject,
                            String toEmail, HashMap<String, String> parameters);
}
