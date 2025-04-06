package site.easy.to.build.crm.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Id;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "v_ticket_depense") 
public class TicketDepenseDetails {
    
    @Id
    @Column(name = "ticket_id", nullable = false)
    private Long ticketId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "priority")
    private String priority;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "depense_id")
    private Long depenseId;

    @Column(name = "valeur_depense")
    private Double valeurDepense;

    @Column(name = "date_depense")
    private LocalDateTime dateDepense;

    @Column(name = "etat")
    private String etat;

    @Column(name = "lead_id")
    private Long leadId;
}
