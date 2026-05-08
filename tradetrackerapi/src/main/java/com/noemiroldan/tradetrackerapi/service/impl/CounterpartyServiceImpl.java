package com.noemiroldan.tradetrackerapi.service.impl;

import com.noemiroldan.tradetrackerapi.dto.request.CounterpartyRequestDto;
import com.noemiroldan.tradetrackerapi.dto.response.CounterpartyResponseDto;
import com.noemiroldan.tradetrackerapi.service.CounterpartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CounterpartyServiceImpl implements CounterpartyService {
    @Override
    public CounterpartyResponseDto getCounterpartyById(Integer id) {
        return null;
    }

    @Override
    public CounterpartyResponseDto createCounterparty(CounterpartyRequestDto request) {
        return null;
    }
}
