package com.noemiroldan.tradetrackerapi.service;

import com.noemiroldan.tradetrackerapi.dto.request.CounterpartyRequestDto;
import com.noemiroldan.tradetrackerapi.dto.response.CounterpartyResponseDto;

public interface CounterpartyService {
    CounterpartyResponseDto getCounterpartyById(Integer counterpartyId);
    CounterpartyResponseDto createCounterparty(CounterpartyRequestDto request);
}
