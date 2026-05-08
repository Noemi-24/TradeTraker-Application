package com.noemiroldan.tradetrackerapi.service.impl;

import com.noemiroldan.tradetrackerapi.dto.request.TradeRequestDto;
import com.noemiroldan.tradetrackerapi.dto.response.TradeResponseDto;
import com.noemiroldan.tradetrackerapi.entity.Counterparty;
import com.noemiroldan.tradetrackerapi.entity.Trade;
import com.noemiroldan.tradetrackerapi.exception.ResourceNotFoundException;
import com.noemiroldan.tradetrackerapi.mapper.TradeMapper;
import com.noemiroldan.tradetrackerapi.repository.CounterpartyRepository;
import com.noemiroldan.tradetrackerapi.repository.TradeRepository;
import com.noemiroldan.tradetrackerapi.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {
    private final TradeRepository tradeRepository;
    private final CounterpartyRepository counterpartyRepository;
    private final TradeMapper tradeMapper;
    private static final Logger logger = LoggerFactory.getLogger(TradeServiceImpl.class);

    @Override
    public TradeResponseDto getTradeById(Integer id) {
        logger.info("Fetching trade with ID: {}", id);
        Trade trade = tradeRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Trade not found with ID: {}", id);
                    return new ResourceNotFoundException("Trade", "id", id);
                });
        logger.info("Trade found with ID: {}", id);

        return tradeMapper.toTradeResponseDto(trade);
    }

    @Override
    public TradeResponseDto createTrade(TradeRequestDto request) {
        Integer id = request.getCounterpartyId();
        Counterparty counterparty = counterpartyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Counterparty", "id", id));

        Trade trade = tradeMapper.toTrade(request, counterparty);

        Trade savedTrade = tradeRepository.save(trade);

        return tradeMapper.toTradeResponseDto(savedTrade);
    }

}
