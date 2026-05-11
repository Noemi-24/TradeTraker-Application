package com.noemiroldan.tradetrackerapi.repository;

import com.noemiroldan.tradetrackerapi.entity.Trade;
import com.noemiroldan.tradetrackerapi.enums.TradeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {
    List<Trade> findByStatus(TradeStatus status);
}
