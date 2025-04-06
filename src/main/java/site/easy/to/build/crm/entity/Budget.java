package site.easy.to.build.crm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "budget")
public class Budget {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_id")
    private int id;
    
    @DecimalMin(value = "0.00", inclusive = true, message = "La valeur doit être supérieure ou égale à 0.00")
    @Column(name = "valeur")
    private double valeur;
    
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NotNull(message = "date de changement requis")
    @Column(name = "date_budget")
    private LocalDateTime date;

    public Budget() {
    }

    public Budget(int id, double valeur, Customer customer, LocalDateTime date) {
        this.id = id;
        this.valeur = valeur;
        this.customer = customer;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

