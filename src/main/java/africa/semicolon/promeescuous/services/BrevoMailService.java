package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.request.EmailNotificationRequest;
import africa.semicolon.promeescuous.dto.response.EmailNotificationResponse;
import org.springframework.stereotype.Service;

@Service
public class BrevoMailService implements MailServices{
    @Override
    public EmailNotificationResponse send(EmailNotificationRequest emailNotificationRequest) {
        return null;
    }
}

