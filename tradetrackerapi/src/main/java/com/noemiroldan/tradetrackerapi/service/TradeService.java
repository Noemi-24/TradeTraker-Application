package com.noemiroldan.tradetrackerapi.service;

import com.noemiroldan.tradetrackerapi.dto.request.TradeRequestDto;
import com.noemiroldan.tradetrackerapi.dto.response.TradeResponseDto;
import com.noemiroldan.tradetrackerapi.enums.TradeStatus;

import java.util.List;

public interface TradeService {
    TradeResponseDto getTradeById(Integer id);
    TradeResponseDto createTrade(TradeRequestDto request);
    List<TradeResponseDto> getAllTrades();
    List<TradeResponseDto> findByStatus(TradeStatus status);
}
