package com.noemiroldan.tradetrackerapi.controller;

import com.noemiroldan.tradetrackerapi.dto.request.CounterpartyRequestDto;
import com.noemiroldan.tradetrackerapi.dto.response.CounterpartyResponseDto;
import com.noemiroldan.tradetrackerapi.service.CounterpartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/counterparties")
@RequiredArgsConstructor
public class CounterpartyController {
    public final CounterpartyService counterpartyService;

    @GetMapping("/{counterpartyId}")
    public ResponseEntity<CounterpartyResponseDto> getCounterpartyById(@PathVariable Integer counterpartyId){
        return ResponseEntity.ok(counterpartyService.getCounterpartyById(counterpartyId));
    }

    @PostMapping
    public ResponseEntity<CounterpartyResponseDto> createCounterparty(@RequestBody CounterpartyRequestDto request){
        CounterpartyResponseDto responseDto = counterpartyService.createCounterparty(request);
        return ResponseEntity.ok(responseDto);
    }
}
