package com.talentscore.dto;

import java.math.BigDecimal;

public class EmployeeDTO {
    public Long id;
    public String name;
    public String email;
    public String primaryRole;
    public String roleType; // ADMIN or USER
    public boolean isOpenToHiring;
    public BigDecimal lifetimeCredits;

    public EmployeeDTO() {}
    public EmployeeDTO(Long id, String name, String email, String primaryRole, String roleType, boolean isOpenToHiring, BigDecimal lifetimeCredits) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.primaryRole = primaryRole;
        this.roleType = roleType;
        this.isOpenToHiring = isOpenToHiring;
        this.lifetimeCredits = lifetimeCredits;
    }
}