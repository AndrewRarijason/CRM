package site.easy.to.build.crm.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.Notification;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    
    Notification findByNotificationId(int notificationId);

    List<Notification> findByCustomerCustomerId(int customerId);

    List<Notification> findByEtat(int etat);

    List<Notification> findAll();

    List<Notification> findByCustomerCustomerIdOrderByDateNotificationDesc(int customerId, Pageable pageable);

    long countByCustomerCustomerId(int customerId);

    List<Notification> findByCustomerCustomerIdAndEtatOrderByDateNotificationDesc(int customerId, int etat);


}
