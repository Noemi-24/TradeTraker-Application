package com.noemiroldan.tradetrackerapi.mapper;

import com.noemiroldan.tradetrackerapi.dto.request.CounterpartyRequestDto;
import com.noemiroldan.tradetrackerapi.dto.response.CounterpartyResponseDto;
import com.noemiroldan.tradetrackerapi.entity.Counterparty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CounterpartyMapper {
    public CounterpartyResponseDto toCounterpartyResponseDto(Counterparty counterparty) {
        CounterpartyResponseDto counterpartyResponseDto = new CounterpartyResponseDto();

        counterpartyResponseDto.setCounterpartyId(counterparty.getCounterpartyId());
        counterpartyResponseDto.setName(counterparty.getName());
        counterpartyResponseDto.setCountry(counterparty.getCountry());
        counterpartyResponseDto.setRiskRating(counterparty.getRiskRating());

        return counterpartyResponseDto;
    }

    public Counterparty toCounterparty(CounterpartyRequestDto request) {
        Counterparty counterparty = new Counterparty();

        counterparty.setCountry(request.getCountry());
        counterparty.setName(request.getName());
        counterparty.setRiskRating(request.getRiskRating());

        return counterparty;
    }
}
