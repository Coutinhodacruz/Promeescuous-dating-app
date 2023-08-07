package africa.semicolon.promeescuous.services;


import africa.semicolon.promeescuous.dto.request.EmailNotificationRequest;
import africa.semicolon.promeescuous.dto.response.EmailNotificationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MailServicesTest {

    @Autowired
    private MailServices mailServices;

    @Test
    public void testThatEmailSendingWorks(){
        String email ="kiseno6508@quipas.com";
        EmailNotificationRequest request = new EmailNotificationRequest();
        EmailNotificationResponse notificationResponse = mailServices.send(request);

        assertNotNull(notificationResponse);
    }

}
