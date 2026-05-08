package com.noemiroldan.tradetrackerapi.mapper;

import com.noemiroldan.tradetrackerapi.dto.request.TradeRequestDto;
import com.noemiroldan.tradetrackerapi.dto.response.TradeResponseDto;
import com.noemiroldan.tradetrackerapi.entity.Counterparty;
import com.noemiroldan.tradetrackerapi.entity.Trade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TradeMapper {
    public TradeResponseDto toTradeResponseDto(Trade trade) {
       TradeResponseDto tradeResponseDto = new TradeResponseDto();

       tradeResponseDto.setTradeId(trade.getTradeId());
       tradeResponseDto.setCounterpartyId(trade.getCounterparty().getCounterpartyId());
       tradeResponseDto.setCounterpartyName(trade.getCounterparty().getName());
       tradeResponseDto.setAmount(trade.getAmount());
       tradeResponseDto.setStatus(trade.getStatus());
       tradeResponseDto.setAssetType(trade.getAssetType());
       tradeResponseDto.setCurrency(trade.getCurrency());

       return tradeResponseDto;
    }

    public Trade toTrade(TradeRequestDto request, Counterparty counterparty) {
        Trade trade = new Trade();

        trade.setCounterparty(counterparty);
        trade.setAssetType(request.getAssetType());
        trade.setTradeDate(request.getTradeDate());
        trade.setSettlementDate(request.getSettlementDate());
        trade.setAmount(request.getAmount());
        trade.setCurrency(request.getCurrency());

        return trade;
    }
}
