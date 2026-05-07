package com.noemiroldan.tradetrackerapi.service.impl;

import com.noemiroldan.tradetrackerapi.dto.response.TradeResponseDto;
import com.noemiroldan.tradetrackerapi.entity.Counterparty;
import com.noemiroldan.tradetrackerapi.entity.Trade;
import com.noemiroldan.tradetrackerapi.enums.*;
import com.noemiroldan.tradetrackerapi.mapper.TradeMapper;
import com.noemiroldan.tradetrackerapi.repository.TradeRepository;
import com.noemiroldan.tradetrackerapi.service.TradeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TradeServiceImplTest {

    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private TradeMapper tradeMapper;

    @InjectMocks
    private TradeServiceImpl tradeServiceImpl;

    @Test
    void getTradeById_Success() {
        // ARRANGE
        TradeResponseDto responseDto = new TradeResponseDto();
        responseDto.setTradeId(1);
        responseDto.setCounterpartyName("counterpartyName");
        responseDto.setAssetType(AssetType.BOND);
        responseDto.setAmount(BigDecimal.valueOf(100));
        responseDto.setCurrency(Currency.USD);
        responseDto.setStatus(TradeStatus.PENDING);

        Counterparty counterparty = new Counterparty();
        counterparty.setName("counterpartyName");
        counterparty.setCounterpartyId(1);
        counterparty.setCountry(Country.USA);
        counterparty.setRiskRating(RiskRating.LOW);

        Trade trade = new Trade();
        trade.setTradeId(1);
        trade.setCounterpartyId(counterparty);
        trade.setAssetType(AssetType.BOND);
        trade.setAmount(BigDecimal.valueOf(100));
        trade.setCurrency(Currency.USD);
        trade.setStatus(TradeStatus.PENDING);
        trade.setTradeDate(LocalDate.of(2024, 1, 1));
        trade.setSettlementDate(LocalDate.of(2024, 1, 1));

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
    void createTrade() {
    }
}