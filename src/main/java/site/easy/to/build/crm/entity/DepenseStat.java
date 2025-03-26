package site.easy.to.build.crm.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "v_depense_stat")
@Immutable // Indique que cette entité est en lecture seule
public class DepenseStat {

    @Id
    private Long id; // ID fictif pour Hibernate

    @Column(name = "total_lead_depense")
    private Double totalLeadDepense;

    @Column(name = "total_ticket_depense")
    private Double totalTicketDepense;

    @Column(name = "total_depense")
    private Double totalDepense;

    @Column(name = "pourcentage_lead")
    private Double pourcentageLead;

    @Column(name = "pourcentage_ticket")
    private Double pourcentageTicket;

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalLeadDepense() {
        return totalLeadDepense;
    }

    public void setTotalLeadDepense(Double totalLeadDepense) {
        this.totalLeadDepense = totalLeadDepense;
    }

    public Double getTotalTicketDepense() {
        return totalTicketDepense;
    }

    public void setTotalTicketDepense(Double totalTicketDepense) {
        this.totalTicketDepense = totalTicketDepense;
    }

    public Double getTotalDepense() {
        return totalDepense;
    }

    public void setTotalDepense(Double totalDepense) {
        this.totalDepense = totalDepense;
    }

    public Double getPourcentageLead() {
        return pourcentageLead;
    }

    public void setPourcentageLead(Double pourcentageLead) {
        this.pourcentageLead = pourcentageLead;
    }

    public Double getPourcentageTicket() {
        return pourcentageTicket;
    }

    public void setPourcentageTicket(Double pourcentageTicket) {
        this.pourcentageTicket = pourcentageTicket;
    }
}
