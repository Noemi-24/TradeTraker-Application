package com.noemiroldan.tradetrackerapi.service.impl;

import com.noemiroldan.tradetrackerapi.dto.request.SettlementIssueRequestDto;
import com.noemiroldan.tradetrackerapi.dto.response.SettlementIssueResponseDto;
import com.noemiroldan.tradetrackerapi.entity.Counterparty;
import com.noemiroldan.tradetrackerapi.entity.SettlementIssue;
import com.noemiroldan.tradetrackerapi.entity.Trade;
import com.noemiroldan.tradetrackerapi.enums.*;
import com.noemiroldan.tradetrackerapi.mapper.SettlementIssueMapper;
import com.noemiroldan.tradetrackerapi.repository.SettlementIssueRepository;
import com.noemiroldan.tradetrackerapi.repository.TradeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class SettlementIssueServiceImplTest {
    @Mock
    private SettlementIssueRepository settlementIssueRepository;

    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private SettlementIssueMapper settlementIssueMapper;

    @InjectMocks
    private SettlementIssueServiceImpl settlementIssueServiceImpl;

    @Test
    void resolveSettlementIssue_Success() {
        // ARRANGE

        // ACT

        // ASSERT & VERIFY
    }

    @Test
    void findSettlementIssueByResolved_Success() {
        // ARRANGE
        SettlementIssue settlementIssue = buildValidSettlementIssue();
        settlementIssue.setResolved(true);
        SettlementIssueResponseDto response = buildSettlementIssueResponse();

        when(settlementIssueRepository.findByResolved(true)).thenReturn(List.of(settlementIssue));
        when(settlementIssueMapper.toSettlementIssueResponseDto(settlementIssue)).thenReturn(response);

        // ACT
        List<SettlementIssueResponseDto> result = settlementIssueServiceImpl.findByResolved(true);

        // ASSERT & VERIFY
        assertNotNull(result);
        assertEquals(result.getFirst(), response);
        verify(settlementIssueRepository).findByResolved(true);
        verify(settlementIssueMapper).toSettlementIssueResponseDto(settlementIssue);
    }

    @Test
    void createSettlementIssue_Success() {
        // ARRANGE
        Trade trade = buildValidTrade();
        SettlementIssueRequestDto request = buildValidSettlementIssueRequest();
        SettlementIssue settlementIssue = buildValidSettlementIssue();
        SettlementIssueResponseDto response = buildSettlementIssueResponse();

        when(tradeRepository.findById(trade.getTradeId())).thenReturn(Optional.of(trade));
        when(settlementIssueMapper.toSettlementIssue(request, trade)).thenReturn(settlementIssue);
        when(settlementIssueRepository.save(settlementIssue)).thenReturn(settlementIssue);
        when(settlementIssueMapper.toSettlementIssueResponseDto(settlementIssue)).thenReturn(response);

        //ACT
        SettlementIssueResponseDto result = settlementIssueServiceImpl.createSettlementIssue(request, trade.getTradeId());

        // ASSERT & VERIFY
        assertNotNull(result);
        assertEquals(response, result);
        verify(tradeRepository).findById(trade.getTradeId());
        verify(settlementIssueRepository).save(settlementIssue);
        verify(settlementIssueMapper).toSettlementIssue(request,trade);
        verify(settlementIssueMapper).toSettlementIssueResponseDto(settlementIssue);
    }

    private SettlementIssue buildValidSettlementIssue (){
        SettlementIssue settlementIssue = new SettlementIssue();

        settlementIssue.setSettlementId(1);
        settlementIssue.setResolved(false);
        settlementIssue.setReasonCode(ReasonCode.INSUFFICIENT_FUNDS);
        settlementIssue.setDescription("Counterparty account balance was insufficient for settlement.");
        settlementIssue.setSeverity(Severity.HIGH);
        settlementIssue.setCreatedAt(LocalDateTime.now());
        settlementIssue.setTrade(buildValidTrade());

        return settlementIssue;
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

    private SettlementIssueRequestDto buildValidSettlementIssueRequest(){
        SettlementIssueRequestDto request = new SettlementIssueRequestDto();

        request.setDescription("Counterparty account balance was insufficient for settlement.");
        request.setReasonCode(ReasonCode.INSUFFICIENT_FUNDS);
        request.setSeverity(Severity.HIGH);

        return request;
    }

    private SettlementIssueResponseDto buildSettlementIssueResponse(){
        SettlementIssueResponseDto response = new SettlementIssueResponseDto();

        response.setSettlementId(1);
        response.setTradeId(buildValidTrade().getTradeId());
        response.setReasonCode(ReasonCode.INSUFFICIENT_FUNDS);
        response.setDescription("Counterparty account balance was insufficient for settlement.");
        response.setSeverity(Severity.HIGH);
        response.setCreatedAt(LocalDateTime.now());
        response.setResolved(false);

        return response;
    }
}