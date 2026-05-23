package com.talentscore.repository;

import com.talentscore.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Custom Derived Query Finder Method
     * Scans the database table and instantly compiles a list of available candidates
     * filtered by their employment target roles while completely ignoring text casing.
     */
    List<Employee> findByIsOpenToHiringTrueAndPrimaryRoleContainingIgnoreCase(String primaryRole);
}