package com.noemiroldan.tradetrackerapi.service.impl;

import com.noemiroldan.tradetrackerapi.dto.request.SettlementIssueRequestDto;
import com.noemiroldan.tradetrackerapi.dto.response.SettlementIssueResponseDto;
import com.noemiroldan.tradetrackerapi.entity.SettlementIssue;
import com.noemiroldan.tradetrackerapi.entity.Trade;
import com.noemiroldan.tradetrackerapi.exception.ResourceNotFoundException;
import com.noemiroldan.tradetrackerapi.mapper.SettlementIssueMapper;
import com.noemiroldan.tradetrackerapi.repository.SettlementIssueRepository;
import com.noemiroldan.tradetrackerapi.repository.TradeRepository;
import com.noemiroldan.tradetrackerapi.service.SettlementIssueService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementIssueServiceImpl implements SettlementIssueService {
    private final SettlementIssueRepository settlementIssueRepository;
    private final TradeRepository tradeRepository;
    private final SettlementIssueMapper settlementIssueMapper;
    private static final Logger logger = LoggerFactory.getLogger(SettlementIssueServiceImpl.class);


    @Override
    public SettlementIssueResponseDto updateSettlementIssue(Integer id) {
        logger.info("Fetching settlement issue with ID: {}", id);
        SettlementIssue settlementIssue = settlementIssueRepository.findById(id)
                .orElseThrow(()-> {
                    logger.warn("SettlementIssue not found with ID: {}", id );
                    return new ResourceNotFoundException("SettlementIssue", "id", id);
                });

        settlementIssue.setResolved(true);

        SettlementIssue settlementIssueUpdated = settlementIssueRepository.save(settlementIssue);
        logger.info("settlement issue updated successfully with ID: {}", settlementIssue.getSettlementId());

        return settlementIssueMapper.toSettlementIssueResponseDto(settlementIssueUpdated);
    }

    @Override
    public List<SettlementIssueResponseDto> findByResolved(Boolean resolved) {
        logger.info("Fetching settlement issues for resolved status {}", resolved);
        return settlementIssueRepository.findByResolved(resolved)
                .stream()
                .map(settlementIssueMapper::toSettlementIssueResponseDto)
                .toList();
    }

    @Override
    public SettlementIssueResponseDto createSettlementIssue(SettlementIssueRequestDto request, Integer tradeId) {
        logger.info("Fetching trade with ID: {}", tradeId);
        Trade trade = tradeRepository.findById(tradeId)
                .orElseThrow(() -> {
                    logger.warn("Trade not found with ID: {}", tradeId);
                    return new ResourceNotFoundException("Trade", "id", tradeId);
                });
        logger.info("Trade found with ID: {}", tradeId);

        SettlementIssue settlementIssue = settlementIssueMapper.toSettlementIssue(request, trade);

        SettlementIssue savedSettlementIssue = settlementIssueRepository.save(settlementIssue);
        logger.info("Settlement Issue created successfully with ID: {}", savedSettlementIssue.getSettlementId());

        return settlementIssueMapper.toSettlementIssueResponseDto(savedSettlementIssue);
    }
}
