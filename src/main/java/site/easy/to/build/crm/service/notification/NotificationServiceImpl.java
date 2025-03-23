package site.easy.to.build.crm.service.notification;

import org.springframework.stereotype.Service;
import site.easy.to.build.crm.repository.NotificationRepository;
import site.easy.to.build.crm.entity.Notification;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification findByNotificationId(int notificationId) {
        return notificationRepository.findByNotificationId(notificationId);
    }

    @Override
    public List<Notification> findByCustomerId(int customerId) {
        return notificationRepository.findByCustomerCustomerId(customerId);
    }

    @Override
    public List<Notification> findByEtat(int etat) {
        return notificationRepository.findByEtat(etat);
    }

    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void delete(Notification notification) {
        notificationRepository.delete(notification);
    }

    @Override
    public List<Notification> getRecentNotifications(int customerId) {
        return notificationRepository.findByCustomerCustomerIdAndEtatOrderByDateNotificationDesc(customerId, 0);
    }

    @Override
    public long countByCustomerId(int customerId) {
        return notificationRepository.countByCustomerCustomerId(customerId);
    }

    @Override
    public void updateNotificationEtat(int notificationId, int newEtat) {
        Notification notification = notificationRepository.findByNotificationId(notificationId);
        if (notification != null) {
            notification.setEtat(newEtat);
            notificationRepository.save(notification);
        }
    }
}
