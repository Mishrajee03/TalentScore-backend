package com.talentscore.repository;

import com.talentscore.entity.CreditIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditIssueRepository extends JpaRepository<CreditIssue, Long> {

    /**
     * Custom Derived Relationship Query
     * Automates an inner database join using JPA entity mapping rules.
     * Looks up the underlying Employee object by its Primary Key ID and
     * returns every individual point transaction ledger row assigned to them.
     */
    List<CreditIssue> findByEmployeeId(Long employeeId);
}