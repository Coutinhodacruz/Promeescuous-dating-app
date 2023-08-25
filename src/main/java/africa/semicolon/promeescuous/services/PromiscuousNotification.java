package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.request.NotificationRequest;
import africa.semicolon.promeescuous.dto.response.NotificationResponse;
import africa.semicolon.promeescuous.model.Notification;
import africa.semicolon.promeescuous.model.User;
import africa.semicolon.promeescuous.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.promeescuous.exception.ExceptionMessage.NOTIFICATION_SENT_SUCCESSFULLY;

@Service
@AllArgsConstructor
@Slf4j
public class PromiscuousNotification implements NotificationService{
    @Autowired
    private final UserServices userService;
    private final NotificationRepository notificationRepository;
    @Override
    public NotificationResponse sendNotification(NotificationRequest notificationRequest) {
        Long sender_id = notificationRequest.getSender_id();
        Long receivers_id = notificationRequest.getRecipients_id();
        String header = notificationRequest.getSubject();

        Notification notification = new Notification();
        notification.setSender(sender_id);

        User foundUser = userService.findUserById(receivers_id);
        notification.setUser(foundUser);

        notification.setContent(header);

        notificationRepository.save(notification);

        return NotificationResponse.builder()
                .message(NOTIFICATION_SENT_SUCCESSFULLY.name())
                .build();
    }
}