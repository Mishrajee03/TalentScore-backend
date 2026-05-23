package com.talentscore.service;

import com.talentscore.dto.CreditIssueDTO;
import com.talentscore.entity.CreditIssue;
import com.talentscore.entity.Employee;
import com.talentscore.repository.CreditIssueRepository;
import com.talentscore.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditIssueService {
    private final CreditIssueRepository issueRepo;
    private final EmployeeRepository empRepo;

    public CreditIssueService(CreditIssueRepository issueRepo, EmployeeRepository empRepo) {
        this.issueRepo = issueRepo;
        this.empRepo = empRepo;
    }

    public List<CreditIssueDTO> getAllEntries() {
        return issueRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<CreditIssueDTO> getEntriesByEmployee(Long empId) {
        return issueRepo.findByEmployeeId(empId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public CreditIssueDTO createEntry(CreditIssueDTO dto) {
        Employee emp = empRepo.findById(dto.employeeId).orElseThrow(() -> new RuntimeException("Target profile matching entry not found"));

        CreditIssue issue = new CreditIssue();
        issue.setEmployee(emp);
        issue.setCreditPoints(dto.creditPoints);
        issue.setCategory(dto.category);
        issue.setReason(dto.reason);

        // Accumulate points onto baseline user profile tracking fields
        emp.setLifetimeCredits(emp.getLifetimeCredits().add(dto.creditPoints));
        empRepo.save(emp);

        return toDTO(issueRepo.save(issue));
    }

    @Transactional
    public CreditIssueDTO updateEntry(Long id, CreditIssueDTO dto) {
        CreditIssue issue = issueRepo.findById(id).orElseThrow(() -> new RuntimeException("Ledger entry element missing"));
        Employee emp = issue.getEmployee();

        // Roll back old valuation from total profile metrics balance map
        emp.setLifetimeCredits(emp.getLifetimeCredits().subtract(issue.getCreditPoints()));

        // Re-apply updated details
        issue.setCreditPoints(dto.creditPoints);
        issue.setCategory(dto.category);
        issue.setReason(dto.reason);

        emp.setLifetimeCredits(emp.getLifetimeCredits().add(dto.creditPoints));
        empRepo.save(emp);

        return toDTO(issueRepo.save(issue));
    }

    @Transactional
    public void deleteEntry(Long id) {
        CreditIssue issue = issueRepo.findById(id).orElseThrow(() -> new RuntimeException("Ledger entry element missing"));
        Employee emp = issue.getEmployee();

        // Subtract target criteria balances prior to decoupling hard row records
        emp.setLifetimeCredits(emp.getLifetimeCredits().subtract(issue.getCreditPoints()));
        empRepo.save(emp);

        issueRepo.delete(issue);
    }

    private CreditIssueDTO toDTO(CreditIssue issue) {
        return new CreditIssueDTO(issue.getId(), issue.getEmployee().getId(), issue.getEmployee().getName(), issue.getCreditPoints(), issue.getCategory(), issue.getReason(), issue.getIssuedAt());
    }
}