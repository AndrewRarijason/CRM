package site.easy.to.build.crm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import java.time.LocalDateTime;

@Entity
@Table(name = "depense")
public class Depense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer depenseId;

    
    @DecimalMin(value = "0.01", inclusive = true, message = "La valeur de la dépense doit être supérieure à 0.00")
    @Column(name = "valeur_depense", nullable = false, precision = 10, scale = 2)
    private double valeurDepense;

    @Column(name = "date_depense", nullable = false)
    private LocalDateTime dateDepense;


    @Column(name = "etat", nullable = false)
    private Integer etat;

    @ManyToOne
    @JoinColumn(name = "lead_id")
    private Lead lead;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public Integer getDepenseId() {
        return depenseId;
    }

    public void setDepenseId(Integer depenseId) {
        this.depenseId = depenseId;
    }

    public double getValeurDepense() {
        return valeurDepense;
    }

    public void setValeurDepense(double valeurDepense) {
        this.valeurDepense = valeurDepense;
    }

    public LocalDateTime getDateDepense() {
        return dateDepense;
    }

    public void setDateDepense(LocalDateTime dateDepense) {
        this.dateDepense = dateDepense;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public Lead getLead() {
        return lead;
    }

    public void setLead(Lead lead) {
        this.lead = lead;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    // Getters and Setters
}