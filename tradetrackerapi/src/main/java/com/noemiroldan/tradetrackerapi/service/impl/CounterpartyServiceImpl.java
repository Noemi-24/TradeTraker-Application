package com.noemiroldan.tradetrackerapi.service.impl;

import com.noemiroldan.tradetrackerapi.dto.request.CounterpartyRequestDto;
import com.noemiroldan.tradetrackerapi.dto.response.CounterpartyResponseDto;
import com.noemiroldan.tradetrackerapi.entity.Counterparty;
import com.noemiroldan.tradetrackerapi.exception.ResourceNotFoundException;
import com.noemiroldan.tradetrackerapi.mapper.CounterpartyMapper;
import com.noemiroldan.tradetrackerapi.repository.CounterpartyRepository;
import com.noemiroldan.tradetrackerapi.service.CounterpartyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CounterpartyServiceImpl implements CounterpartyService {
    private final CounterpartyRepository counterpartyRepository;
    private final CounterpartyMapper counterpartyMapper;
    private static final Logger logger = LoggerFactory.getLogger(CounterpartyServiceImpl.class);

    @Override
    public CounterpartyResponseDto getCounterpartyById(Integer counterpartyId) {
        logger.info("Fetching counterparty with ID: {}", counterpartyId);
        Counterparty counterparty = counterpartyRepository.findById(counterpartyId)
                .orElseThrow(()->{
                    logger.warn("Counterparty not found with ID: {}", counterpartyId);
                    return new ResourceNotFoundException("Counterparty", "id", counterpartyId);
                });
        logger.info("Counterparty found with ID: {}", counterpartyId);
        return counterpartyMapper.toCounterpartyResponseDto(counterparty);
    }

    @Override
    public CounterpartyResponseDto createCounterparty(CounterpartyRequestDto request) {
        logger.info("Creating counterparty with request: {}", request);
        Counterparty counterparty = counterpartyMapper.toCounterparty(request);
        Counterparty savedCounterparty = counterpartyRepository.save(counterparty);

        logger.info("Counterparty created successfully with ID: {}", savedCounterparty.getCounterpartyId());
        return counterpartyMapper.toCounterpartyResponseDto(savedCounterparty);
    }
}
