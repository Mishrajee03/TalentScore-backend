package com.talentscore.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "credit_issues")
public class CreditIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Establishing a JPA Relationship Mapping linked straight to the primary employee profile
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal creditPoints;

    @Column(nullable = false)
    private String category; // e.g., TECHNICAL, DELIVERY, COLLABORATION

    @Column(nullable = false)
    private String reason; // Audit trail justification description

    @Column(nullable = false)
    private LocalDateTime issuedAt;

    // Constructors
    public CreditIssue() {}

    /**
     * Automatic Lifecycle Callback
     * Guarantees a clean, un-falsifiable timestamp is appended automatically
     * to the ledger entry row row right before it writes to the H2 database.
     */
    @PrePersist
    protected void onCreate() {
        this.issuedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public BigDecimal getCreditPoints() { return creditPoints; }
    public void setCreditPoints(BigDecimal creditPoints) { this.creditPoints = creditPoints; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public LocalDateTime getIssuedAt() { return issuedAt; }
    public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }
}