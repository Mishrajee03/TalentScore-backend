package com.talentscore.controller;

import com.talentscore.dto.CreditIssueDTO;
import com.talentscore.dto.EmployeeDTO;
import com.talentscore.service.CreditIssueService;
import com.talentscore.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final EmployeeService empService;
    private final CreditIssueService issueService;

    public AdminController(EmployeeService empService, CreditIssueService issueService) {
        this.empService = empService;
        this.issueService = issueService;
    }

    // Comprehensive Management Routes Over Candidate Profiles
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> listAll() { return ResponseEntity.ok(empService.getAll()); }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDTO> onboard(@RequestBody EmployeeDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(empService.create(dto)); }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> modifyProfile(@PathVariable Long id, @RequestBody EmployeeDTO dto) { return ResponseEntity.ok(empService.update(id, dto)); }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> purgeProfile(@PathVariable Long id) { empService.delete(id); return ResponseEntity.noContent().build(); }

    // Full CRUD Routing Configurations Mapping Over Score Transactions
    @GetMapping("/entries")
    public ResponseEntity<List<CreditIssueDTO>> listAllEntries() { return ResponseEntity.ok(issueService.getAllEntries()); }

    @PostMapping("/entries")
    public ResponseEntity<CreditIssueDTO> logEntry(@RequestBody CreditIssueDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(issueService.createEntry(dto)); }

    @PutMapping("/entries/{id}")
    public ResponseEntity<CreditIssueDTO> modifyEntry(@PathVariable Long id, @RequestBody CreditIssueDTO dto) { return ResponseEntity.ok(issueService.updateEntry(id, dto)); }

    @DeleteMapping("/entries/{id}")
    public ResponseEntity<Void> clearEntry(@PathVariable Long id) { issueService.deleteEntry(id); return ResponseEntity.noContent().build(); }
}