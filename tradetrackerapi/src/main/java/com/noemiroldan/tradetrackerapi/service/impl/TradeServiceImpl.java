package com.noemiroldan.tradetrackerapi.service.impl;

import com.noemiroldan.tradetrackerapi.dto.request.TradeRequestDto;
import com.noemiroldan.tradetrackerapi.dto.response.TradeResponseDto;
import com.noemiroldan.tradetrackerapi.entity.Counterparty;
import com.noemiroldan.tradetrackerapi.entity.Trade;
import com.noemiroldan.tradetrackerapi.enums.TradeStatus;
import com.noemiroldan.tradetrackerapi.exception.BadRequestException;
import com.noemiroldan.tradetrackerapi.exception.ResourceNotFoundException;
import com.noemiroldan.tradetrackerapi.mapper.TradeMapper;
import com.noemiroldan.tradetrackerapi.repository.CounterpartyRepository;
import com.noemiroldan.tradetrackerapi.repository.TradeRepository;
import com.noemiroldan.tradetrackerapi.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

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
        logger.info("Creating trade with request: {}", request);
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            logger.warn("Invalid trade amount: {}", request.getAmount());
            throw new BadRequestException("Amount must be greater than zero");
        }

        if (request.getSettlementDate().isBefore(request.getTradeDate())){
            logger.warn("Invalid settlement date. Settlement date cannot be before trade date");
            throw new BadRequestException("Settlement date cannot be before trade date");
        }

        Integer id = request.getCounterpartyId();
        logger.info("Fetching counterparty with ID: {}", id);
        Counterparty counterparty = counterpartyRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Counterparty not found with ID: {}", id );
                    return new ResourceNotFoundException("Counterparty", "id", id);
                });
        logger.info("Creating trade for counterparty ID: {}", request.getCounterpartyId());

        Trade trade = tradeMapper.toTrade(request, counterparty);

        Trade savedTrade = tradeRepository.save(trade);
        logger.info("Trade created successfully with ID: {}", savedTrade.getTradeId());

        return tradeMapper.toTradeResponseDto(savedTrade);
    }

    @Override
    public List<TradeResponseDto> getAllTrades() {
        return tradeRepository.findAll()
                .stream()
                .map(tradeMapper::toTradeResponseDto)
                .toList();
    }

    @Override
    public List<TradeResponseDto> findByStatus(TradeStatus status) {
        return tradeRepository.findByStatus(status)
                .stream()
                .map(tradeMapper::toTradeResponseDto)
                .toList();
    }

}
