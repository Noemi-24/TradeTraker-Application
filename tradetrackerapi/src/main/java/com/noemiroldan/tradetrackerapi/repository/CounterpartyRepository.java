package com.noemiroldan.tradetrackerapi.repository;

import com.noemiroldan.tradetrackerapi.entity.Counterparty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterpartyRepository extends JpaRepository<Counterparty, Integer> {
}
