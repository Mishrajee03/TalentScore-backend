package com.talentscore.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String primaryRole;

    // 1. Add this field for Role-Based Access Control
    private String roleType; // Will store "ADMIN" or "USER"

    private boolean isOpenToHiring = true;
    private BigDecimal lifetimeCredits = BigDecimal.ZERO;

    // Constructors
    public Employee() {}

    public Employee(String name, String email, String primaryRole) {
        this.name = name;
        this.email = email;
        this.primaryRole = primaryRole;
        this.roleType = "USER"; // Default value for new sign-ups
        this.lifetimeCredits = BigDecimal.ZERO;
        this.isOpenToHiring = true;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPrimaryRole() { return primaryRole; }
    public void setPrimaryRole(String primaryRole) { this.primaryRole = primaryRole; }

    // 2. Add these specific Getter and Setter methods for the roleType error
    public String getRoleType() { return roleType; }
    public void setRoleType(String roleType) { this.roleType = roleType; }

    public boolean isOpenToHiring() { return isOpenToHiring; }
    public void setOpenToHiring(boolean openToHiring) { isOpenToHiring = openToHiring; }

    public BigDecimal getLifetimeCredits() { return lifetimeCredits; }
    public void setLifetimeCredits(BigDecimal lifetimeCredits) { this.lifetimeCredits = lifetimeCredits; }
}