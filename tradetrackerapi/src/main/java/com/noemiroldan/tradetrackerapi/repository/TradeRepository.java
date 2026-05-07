package com.noemiroldan.tradetrackerapi.repository;

import com.noemiroldan.tradetrackerapi.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
