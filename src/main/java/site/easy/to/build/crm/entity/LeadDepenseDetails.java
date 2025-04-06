package site.easy.to.build.crm.entity;

import jakarta.persistence.Id;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "v_lead_depense") // Vue SQL
public class LeadDepenseDetails {
    
    @Id
    @Column(name = "lead_id", nullable = false)
    private Long leadId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "status")
    private String status;

    @Column(name = "meeting_id")
    private Long meetingId;

    @Column(name = "google_drive")
    private String googleDrive;

    @Column(name = "google_drive_folder_id")
    private String googleDriveFolderId;

    @Column(name = "lead_created_at")
    private LocalDateTime leadCreatedAt;

    @Column(name = "depense_id")
    private Long depenseId;

    @Column(name = "valeur_depense")
    private Double valeurDepense;

    @Column(name = "date_depense")
    private LocalDateTime dateDepense;

    @Column(name = "etat")
    private String etat;

    @Column(name = "ticket_id")
    private Long ticketId;
}
