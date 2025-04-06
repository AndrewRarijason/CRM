package site.easy.to.build.crm.entity.importcsv;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "customer_request")
public class CustomerRequestImport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_email", nullable = false)
    @NotBlank(message = "Customer email is required")
    @Email(message = "Invalid email format")
    private String customerEmail;

    @Column(name = "subject_or_name", nullable = false)
    @NotBlank(message = "Subject or name is required")
    private String subjectOrName;

    @Column(name = "type", nullable = false)
    @NotBlank(message = "Type is required")
    @Pattern(regexp = "^(lead|ticket)$", message = "Type must be either 'lead' or 'ticket'")
    private String type;

    @Column(name = "status", nullable = false)
    @NotBlank(message = "Status is required")
    @Pattern(
        regexp = "^(meeting-to-schedule|scheduled|archived|success|assign-to-sales|" +
                "open|assigned|on-hold|in-progress|resolved|closed|reopened|" +
                "pending-customer-response|escalated|archived)$",
        message = "Invalid status"
    )
    private String status;

    @Column(name = "expense", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Expense is required")
    private Double expense;

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getSubjectOrName() {
        return subjectOrName;
    }

    public void setSubjectOrName(String subjectOrName) {
        this.subjectOrName = subjectOrName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }
}

