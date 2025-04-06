package site.easy.to.build.crm.service.notification;

import site.easy.to.build.crm.entity.Notification;

import java.util.List;

public interface NotificationService {

    Notification findByNotificationId(int notificationId);

    List<Notification> findByCustomerId(int customerId);

    List<Notification> findByEtat(int etat);

    List<Notification> findAll();

    Notification save(Notification notification);

    void delete(Notification notification);

    List<Notification> getRecentNotifications(int customerId);

    long countByCustomerId(int customerId);

    void updateNotificationEtat(int notificationId, int newEtat);
}
