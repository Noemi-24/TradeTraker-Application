package com.noemiroldan.tradetrackerapi.service.impl;

import com.noemiroldan.tradetrackerapi.dto.request.TradeRequestDto;
import com.noemiroldan.tradetrackerapi.dto.response.TradeResponseDto;
import com.noemiroldan.tradetrackerapi.entity.Trade;
import com.noemiroldan.tradetrackerapi.mapper.TradeMapper;
import com.noemiroldan.tradetrackerapi.repository.TradeRepository;
import com.noemiroldan.tradetrackerapi.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {
    private final TradeRepository tradeRepository;
    private final TradeMapper tradeMapper;

    @Override
    public TradeResponseDto getTradeById(Integer id) {
        Trade trade = tradeRepository.findById(id).orElseThrow();
        return tradeMapper.toTradeResponseDto(trade);
    }

    @Override
    public TradeResponseDto createTrade(TradeRequestDto tradeRequest) {
        Trade trade = new Trade();
        trade.setAmount(tradeRequest.getAmount());
        trade.setAssetType(tradeRequest.getAssetType());
        trade.setTradeDate(tradeRequest.getTradeDate());
        trade.setCurrency(tradeRequest.getCurrency());
        return null;
    }
}
