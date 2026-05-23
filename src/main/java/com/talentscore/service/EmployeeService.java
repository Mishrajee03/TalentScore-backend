package com.talentscore.service;

import com.talentscore.dto.EmployeeDTO;
import com.talentscore.entity.Employee;
import com.talentscore.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository empRepo;

    public EmployeeService(EmployeeRepository empRepo) {
        this.empRepo = empRepo;
    }

    public List<EmployeeDTO> getAll() {
        return empRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public EmployeeDTO getById(Long id) {
        Employee emp = empRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee profile not found"));
        return toDTO(emp);
    }

    @Transactional
    public EmployeeDTO create(EmployeeDTO dto) {
        Employee emp = new Employee(dto.name, dto.email, dto.primaryRole);
        emp.setRoleType(dto.roleType != null ? dto.roleType : "USER");
        emp.setLifetimeCredits(BigDecimal.ZERO);
        return toDTO(empRepo.save(emp));
    }

    @Transactional
    public EmployeeDTO update(Long id, EmployeeDTO dto) {
        Employee emp = empRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee profile not found"));
        emp.setName(dto.name);
        emp.setEmail(dto.email);
        emp.setPrimaryRole(dto.primaryRole);
        emp.setRoleType(dto.roleType);
        emp.setOpenToHiring(dto.isOpenToHiring);
        return toDTO(empRepo.save(emp));
    }

    @Transactional
    public void delete(Long id) {
        if(!empRepo.existsById(id)) throw new RuntimeException("Employee record not found");
        empRepo.deleteById(id);
    }

    public EmployeeDTO toDTO(Employee emp) {
        return new EmployeeDTO(emp.getId(), emp.getName(), emp.getEmail(), emp.getPrimaryRole(), emp.getRoleType(), emp.isOpenToHiring(), emp.getLifetimeCredits());
    }
}