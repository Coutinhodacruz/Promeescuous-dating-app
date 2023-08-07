package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.request.EmailNotificationRequest;
import africa.semicolon.promeescuous.dto.response.EmailNotificationResponse;

public interface MailServices {

    EmailNotificationResponse send(EmailNotificationRequest emailNotificationRequest);
}
