package com.noemiroldan.tradetrackerapi.service;

import com.noemiroldan.tradetrackerapi.dto.request.TradeRequestDto;
import com.noemiroldan.tradetrackerapi.dto.response.TradeResponseDto;

public interface TradeService {
    TradeResponseDto getTradeById(Integer id);
    TradeResponseDto createTrade(TradeRequestDto request);
}
