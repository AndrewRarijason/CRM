package site.easy.to.build.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Integer notificationId;

    @Column(name = "message", length = 250, nullable = false)
    @NotBlank(message = "Le message est obligatoire")
    private String message;

    @Column(name = "date_notification", nullable = false)
    private LocalDateTime dateNotification;

    @Column(name = "etat")
    private Integer etat;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties("notifications")
    private Customer customer;

    @Column(name="id_depense")
    int idDepense;

    public Notification() {
    }

    public Notification(String message, LocalDateTime dateNotification, Integer etat, Customer customer) {
        this.message = message;
        this.dateNotification = dateNotification;
        this.etat = etat;
        this.customer = customer;
    }

    public Notification(Integer notificationId, String message, LocalDateTime dateNotification, Integer etat, Customer customer, int idDepense) {
        this.notificationId = notificationId;
        this.message = message;
        this.dateNotification = dateNotification;
        this.etat = etat;
        this.customer = customer;
        this.idDepense = idDepense;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(LocalDateTime dateNotification) {
        this.dateNotification = dateNotification;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getIdDepense() {
        return idDepense;
    }

    public void setIdDepense(int idDepense) {
        this.idDepense = idDepense;
    }
}
