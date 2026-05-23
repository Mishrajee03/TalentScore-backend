package com.talentscore.controller;

import com.talentscore.dto.CreditIssueDTO;
import com.talentscore.dto.EmployeeDTO;
import com.talentscore.service.CreditIssueService;
import com.talentscore.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final EmployeeService empService;
    private final CreditIssueService issueService;

    public UserController(EmployeeService empService, CreditIssueService issueService) {
        this.empService = empService;
        this.issueService = issueService;
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<EmployeeDTO> fetchProfile(@PathVariable Long id) { return ResponseEntity.ok(empService.getById(id)); }

    @GetMapping("/profile/{id}/performance")
    public ResponseEntity<List<CreditIssueDTO>> fetchPerformanceEntries(@PathVariable Long id) { return ResponseEntity.ok(issueService.getEntriesByEmployee(id)); }
}