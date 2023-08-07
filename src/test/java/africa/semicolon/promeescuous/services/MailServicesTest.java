package africa.semicolon.promeescuous.services;


import africa.semicolon.promeescuous.dto.request.EmailNotificationRequest;
import africa.semicolon.promeescuous.dto.request.Recipient;
import africa.semicolon.promeescuous.dto.request.Sender;
import africa.semicolon.promeescuous.dto.response.EmailNotificationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MailServicesTest {

    @Autowired
    private MailServices mailServices;

    @Test
    public void testThatEmailSendingWorks(){
        String recipientEmail ="dewafof546@weishu8.com";
        String message = "<p>testing our mail services</p>";
        String mailSender = "noreply@promiscuous.com";
        String subject = "test email";

        Recipient recipient = new Recipient();
        recipient.setEmail(recipientEmail);
        List<Recipient> recipients = new ArrayList<>();
        recipients.add(recipient);

        Sender sender = new Sender();
        sender.setEmail(mailSender);

        EmailNotificationRequest request = new EmailNotificationRequest();
        request.setMailContent(message);
        request.setRecipients(recipients);
        request.setSubject(subject);
        request.setSender(sender);


        EmailNotificationResponse notificationResponse = mailServices.send(request);

        assertNotNull(notificationResponse);
    }

}
