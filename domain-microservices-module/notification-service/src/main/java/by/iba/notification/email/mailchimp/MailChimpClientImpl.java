package by.iba.notification.email.mailchimp;

import by.iba.notification.email.mailchimp.http.HttpClient;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class MailChimpClientImpl implements MailChimpClient {

    private final Log log = LogFactory.getLog(getClass());

    @Override
    public boolean sendToMailChimp(String templateSlug, String subject,
                                   String toEmail, HashMap<String, String> parameters) {
        StringBuilder buf = new StringBuilder();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            buf.append(",{");
            buf.append("\"name\":\"").append(entry.getKey()).append("\",");
            buf.append("\"content\":\"").append(entry.getValue()).append("\"");
            buf.append("}");

        }
        String params = buf.substring(1);


        String jsonReq = "{\n" +
                "    \"key\": \"QIbeEXKh5PLZHOoyEVUraA\",\n" +
                "    \"template_name\": \"" + templateSlug + "\",\n" +
                "    \"template_content\": [],\n" +
                "    \"message\": {\n" +
                "        \"subject\": \"" + subject + "\",\n" +
                "        \"from_email\": \"no_reply@trygatsby.com\",\n" +
                "        \"from_name\": \"Gatsby\",\n" +
                "        \"to\": [\n" +
                "            {\n" +
                "                \"email\": \"" + toEmail + "\",\n" +
                "                \"type\": \"to\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"global_merge_vars\": [\n" +
                params +
                "        ]\n" +
                "    },\n" +
                "    \"async\": false\n" +
                "\n" +
                "}";

        Map<String, String> mapHeaders = new HashMap<>();
        mapHeaders.put("Content-Type", "application/json");
        //TODO переписать это на что то другое мб глянуть как собирается внутри этого клиента и подрефактроить если он норм (а он норм вроде)
        String result = new HttpClient().post("https://mandrillapp.com/api/1.0/messages/send-template.json", jsonReq, mapHeaders, new HashMap<>());
        if (result.contains("\"status\":\"sent\"")) {
            log.info("Sent " + templateSlug + " email to " + toEmail);
            return true;
        } else {
            log.error("Problem sending " + templateSlug + " email to " + toEmail);
            return false;
        }
    }
}

