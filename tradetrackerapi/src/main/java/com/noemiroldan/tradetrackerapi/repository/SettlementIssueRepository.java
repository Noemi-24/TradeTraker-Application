package com.noemiroldan.tradetrackerapi.repository;

import com.noemiroldan.tradetrackerapi.entity.SettlementIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettlementIssueRepository extends JpaRepository<SettlementIssue, Integer>{
        List<SettlementIssue> findByResolved(Boolean resolved);
}
