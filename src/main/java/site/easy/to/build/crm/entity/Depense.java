package site.easy.to.build.crm.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "depense")
public class Depense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer depenseId;

    @Column(name = "valeur_depense", nullable = false, precision = 10, scale = 2)
    private double valeurDepense;

    @Column(name = "date_depense", nullable = false)
    private LocalDateTime dateDepense;

    @Column(name = "etat", nullable = false)
    private int etat;

    @ManyToOne
    @JoinColumn(name = "lead_id")
    private Lead lead;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public Depense() {
       
    }
    // Getters and Setters

    public void setDateDepense(LocalDateTime dateDepense) {
        this.dateDepense = dateDepense;
    }

    public void setDepenseId(Integer depenseId) {
        this.depenseId = depenseId;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setLead(Lead lead) {
        this.lead = lead;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setValeurDepense(double valeurDepense) {
        this.valeurDepense = valeurDepense;
    }

    public LocalDateTime getDateDepense() {
        return dateDepense;
    }

    public Integer getDepenseId() {
        return depenseId;
    }

    public int getEtat() {
        return etat;
    }

    public Lead getLead() {
        return lead;
    }
    public Ticket getTicket() {
        return ticket;
    }
    public double getValeurDepense() {
        return valeurDepense;
    }

    
}