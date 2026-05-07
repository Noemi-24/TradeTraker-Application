package com.noemiroldan.tradetrackerapi.controller;

import com.noemiroldan.tradetrackerapi.dto.response.TradeResponseDto;
import com.noemiroldan.tradetrackerapi.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trades")
@RequiredArgsConstructor
public class TradeController {
    public final TradeService tradeService;

    @GetMapping("/{id}")
    public ResponseEntity<TradeResponseDto> getTradeById(@PathVariable Integer id) {
        return ResponseEntity.ok(tradeService.getTradeById(id));
    }
}
