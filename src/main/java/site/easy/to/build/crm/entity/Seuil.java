package site.easy.to.build.crm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "seuil")
public class Seuil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seuil_id")
    private int seuilId;

    @Column(name = "taux", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Le taux est requis")
    @Digits(integer = 10, fraction = 2, message = "Le taux doit être un nombre valide avec jusqu'à 2 décimales")
    @DecimalMin(value = "0.00", inclusive = true, message = "Le taux doit être supérieur ou égal à 0.00")
    private BigDecimal taux;

    @Column(name = "date_seuil")
    private LocalDateTime dateSeuil;

  
    public Seuil() {
    }

    public Seuil(BigDecimal taux, LocalDateTime dateSeuil) {
        this.taux = taux;
        this.dateSeuil = dateSeuil;
    }

    public int getSeuilId() {
        return seuilId;
    }

    public void setSeuilId(int seuilId) {
        this.seuilId = seuilId;
    }

    public BigDecimal getTaux() {
        return taux;
    }

    public void setTaux(BigDecimal taux) {
        this.taux = taux;
    }

    public LocalDateTime getDateSeuil() {
        return dateSeuil;
    }

    public void setDateSeuil(LocalDateTime dateSeuil) {
        this.dateSeuil = dateSeuil;
    }

  

  
}
