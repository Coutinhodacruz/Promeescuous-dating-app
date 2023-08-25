package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.request.NotificationRequest;
import africa.semicolon.promeescuous.dto.response.NotificationResponse;

public interface NotificationService {

     NotificationResponse sendNotification(NotificationRequest notificationRequest);

}
