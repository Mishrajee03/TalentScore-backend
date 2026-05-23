package com.talentscore.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreditIssueDTO {
    public Long id;
    public Long employeeId;
    public String employeeName;
    public BigDecimal creditPoints;
    public String category;
    public String reason;
    public LocalDateTime issuedAt;

    public CreditIssueDTO() {}
    public CreditIssueDTO(Long id, Long employeeId, String employeeName, BigDecimal creditPoints, String category, String reason, LocalDateTime issuedAt) {
        this.id = id;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.creditPoints = creditPoints;
        this.category = category;
        this.reason = reason;
        this.issuedAt = issuedAt;
    }
}