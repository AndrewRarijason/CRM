package site.easy.to.build.crm.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vue_statistique_budget")
public class StatistiqueBudget {

    @Id
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "total_budget")
    private Double totalBudget;

    @Column(name = "total_depense")
    private Double totalDepense;

    @Column(name = "budget_restant")
    private Double budgetRestant;

    @Column(name = "total_depense_ticket")
    private Double totalDepenseTicket;

    @Column(name = "total_depense_lead")
    private Double totalDepenseLead;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public Double getTotalDepense() {
        return totalDepense;
    }

    public void setTotalDepense(Double totalDepense) {
        this.totalDepense = totalDepense;
    }

    public Double getBudgetRestant() {
        return budgetRestant;
    }

    public void setBudgetRestant(Double budgetRestant) {
        this.budgetRestant = budgetRestant;
    }

    public Double getTotalDepenseTicket() {
        return totalDepenseTicket;
    }

    public void setTotalDepenseTicket(Double totalDepenseTicket) {
        this.totalDepenseTicket = totalDepenseTicket;
    }

    public Double getTotalDepenseLead() {
        return totalDepenseLead;
    }

    public void setTotalDepenseLead(Double totalDepenseLead) {
        this.totalDepenseLead = totalDepenseLead;
    }


}