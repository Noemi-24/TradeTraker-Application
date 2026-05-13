package com.noemiroldan.tradetrackerapi.service.impl;

import com.noemiroldan.tradetrackerapi.dto.request.TradeRequestDto;
import com.noemiroldan.tradetrackerapi.dto.response.TradeResponseDto;
import com.noemiroldan.tradetrackerapi.entity.Counterparty;
import com.noemiroldan.tradetrackerapi.entity.Trade;
import com.noemiroldan.tradetrackerapi.enums.*;
import com.noemiroldan.tradetrackerapi.exception.BadRequestException;
import com.noemiroldan.tradetrackerapi.mapper.TradeMapper;
import com.noemiroldan.tradetrackerapi.repository.CounterpartyRepository;
import com.noemiroldan.tradetrackerapi.repository.TradeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TradeServiceImplTest {

    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private CounterpartyRepository counterpartyRepository;

    @Mock
    private TradeMapper tradeMapper;

    @InjectMocks
    private TradeServiceImpl tradeServiceImpl;

    @Test
    void getTradeById_Success() {
        // ARRANGE
        TradeResponseDto responseDto = buildTradeResponse();
        Trade trade = buildValidTrade();

        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));
        when(tradeMapper.toTradeResponseDto(trade)).thenReturn(responseDto);

        // ACT
        TradeResponseDto result = tradeServiceImpl.getTradeById(1);

        // ASSERT & VERIFY
        assertNotNull(result);
        assertEquals(responseDto, result);
        verify(tradeRepository).findById(1);

    }

    @Test
    void createTrade_Success() {
        // ARRANGE
        Counterparty counterparty = buildValidCounterparty();
        TradeRequestDto request = buildValidTradeRequest();
        Trade trade = buildValidTrade();
        TradeResponseDto response = buildTradeResponse();

        when(counterpartyRepository.findById(request.getCounterpartyId())).thenReturn(Optional.of(counterparty));
        when(tradeMapper.toTrade(request, counterparty)).thenReturn(trade);
        when(tradeRepository.save(trade)).thenReturn(trade);
        when(tradeMapper.toTradeResponseDto(trade)).thenReturn(response);


        // ACT
        TradeResponseDto result = tradeServiceImpl.createTrade(request);

        // ASSERT & VERIFY
        assertNotNull(result);
        assertEquals(response, result);
        verify(tradeRepository).save(trade);
        verify(counterpartyRepository).findById(request.getCounterpartyId());
        verify(tradeMapper).toTrade(request, counterparty);
        verify(tradeMapper).toTradeResponseDto(trade);
    }

    @Test
    void createTrade_ShouldReturnPendingStatus_WhenTradeIsCreated() {
        // ARRANGE
        Counterparty counterparty = buildValidCounterparty();
        TradeRequestDto request = buildValidTradeRequest();
        Trade trade = buildValidTrade();
        TradeResponseDto response = buildTradeResponse();

        when(counterpartyRepository.findById(request.getCounterpartyId())).thenReturn(Optional.of(counterparty));
        when(tradeMapper.toTrade(request, counterparty)).thenReturn(trade);
        when(tradeRepository.save(trade)).thenReturn(trade);
        when(tradeMapper.toTradeResponseDto(trade)).thenReturn(response);

        // ACT
        TradeResponseDto result = tradeServiceImpl.createTrade(request);

        // ASSERT & VERIFY
        assertNotNull(result);
        assertEquals(TradeStatus.PENDING, result.getStatus());
    }

    @Test
    void createTrade_ShouldThrowException_WhenAmountIsNotPositive(){
        // ARRANGE
        TradeRequestDto request = buildValidTradeRequest();

        request.setAmount(BigDecimal.valueOf(-1));

        // ACT & ASSERT
        assertThrows(BadRequestException.class, () -> {
            tradeServiceImpl.createTrade(request);
        });

        // VERIFY
        verify(tradeRepository, never()).save(any());
        verify(tradeMapper, never()).toTradeResponseDto(any());
        verify(tradeMapper, never()).toTrade(any(), any());
        verify(counterpartyRepository, never()).findById(any());
    }

    @Test
    void createTrade_ShouldThrowException_WhenSettlementDateIsBeforeTradeDate(){
        // ARRANGE
        TradeRequestDto request = buildValidTradeRequest();

        request.setTradeDate(LocalDate.of(2026, 1, 2));
        request.setSettlementDate(LocalDate.of(2026, 1, 1));

        // ACT & ASSERT
        assertThrows(BadRequestException.class, () -> {
            tradeServiceImpl.createTrade(request);
        });

        // VERIFY
        verify(tradeRepository, never()).save(any());
        verify(tradeMapper, never()).toTradeResponseDto(any());
        verify(tradeMapper, never()).toTrade(any(), any());
        verify(counterpartyRepository, never()).findById(any());

    }

    private TradeRequestDto buildValidTradeRequest(){
        TradeRequestDto request = new TradeRequestDto();

        request.setAssetType(AssetType.BOND);
        request.setAmount(BigDecimal.valueOf(100));
        request.setCurrency(Currency.USD);
        request.setTradeDate(LocalDate.of(2026, 1, 1));
        request.setSettlementDate(LocalDate.of(2026, 1, 2));
        request.setCounterpartyId(buildValidCounterparty().getCounterpartyId());

        return request;
    }

    private Counterparty buildValidCounterparty(){
        Counterparty counterparty = new Counterparty();

        counterparty.setName("counterpartyName");
        counterparty.setCounterpartyId(1);
        counterparty.setCountry(Country.USA);
        counterparty.setRiskRating(RiskRating.LOW);

        return counterparty;
    }

    private Trade buildValidTrade(){
        Trade trade = new Trade();

        trade.setTradeId(1);
        trade.setCounterparty(buildValidCounterparty());
        trade.setAssetType(AssetType.BOND);
        trade.setAmount(BigDecimal.valueOf(100));
        trade.setCurrency(Currency.USD);
        trade.setStatus(TradeStatus.PENDING);
        trade.setTradeDate(LocalDate.of(2026, 1, 1));
        trade.setSettlementDate(LocalDate.of(2026, 1, 1));

        return trade;
    }

    private TradeResponseDto buildTradeResponse(){
        TradeResponseDto response = new TradeResponseDto();

        response.setTradeId(1);
        response.setCounterpartyId(buildValidCounterparty().getCounterpartyId());
        response.setCounterpartyName(buildValidCounterparty().getName());
        response.setAssetType(AssetType.BOND);
        response.setAmount(BigDecimal.valueOf(100));
        response.setCurrency(Currency.USD);
        response.setStatus(TradeStatus.PENDING);

        return response;
    }
}