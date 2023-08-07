package africa.semicolon.promeescuous.repositories;

import africa.semicolon.promeescuous.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
