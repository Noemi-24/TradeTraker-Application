package com.noemiroldan.tradetrackerapi.mapper;

import com.noemiroldan.tradetrackerapi.dto.response.TradeResponseDto;
import com.noemiroldan.tradetrackerapi.entity.Trade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TradeMapper {
    public TradeResponseDto toTradeResponseDto(Trade trade) {
       TradeResponseDto tradeResponseDto = new TradeResponseDto();

       tradeResponseDto.setTradeId(trade.getTradeId());
       tradeResponseDto.setCounterpartyName(trade.getCounterpartyId().getName());
       tradeResponseDto.setAmount(trade.getAmount());
       tradeResponseDto.setStatus(trade.getStatus());
       tradeResponseDto.setAssetType(trade.getAssetType());
       tradeResponseDto.setCurrency(trade.getCurrency());

       return tradeResponseDto;
    }
}
