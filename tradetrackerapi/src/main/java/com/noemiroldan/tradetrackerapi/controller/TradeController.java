package com.noemiroldan.tradetrackerapi.controller;

import com.noemiroldan.tradetrackerapi.dto.request.SettlementIssueRequestDto;
import com.noemiroldan.tradetrackerapi.dto.request.TradeRequestDto;
import com.noemiroldan.tradetrackerapi.dto.response.SettlementIssueResponseDto;
import com.noemiroldan.tradetrackerapi.dto.response.TradeResponseDto;
import com.noemiroldan.tradetrackerapi.enums.TradeStatus;
import com.noemiroldan.tradetrackerapi.service.SettlementIssueService;
import com.noemiroldan.tradetrackerapi.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trades")
@RequiredArgsConstructor
public class TradeController {
    public final TradeService tradeService;
    public final SettlementIssueService settlementIssueService;

    @GetMapping("/{id}")
    public ResponseEntity<TradeResponseDto> getTradeById(@PathVariable Integer id) {
        return ResponseEntity.ok(tradeService.getTradeById(id));
    }

    @PostMapping
    public ResponseEntity<TradeResponseDto> createTrade(@RequestBody TradeRequestDto request) {
        TradeResponseDto tradeResponseDto = tradeService.createTrade(request);
        return ResponseEntity.ok(tradeResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<TradeResponseDto>> getTrades(@RequestParam(required = false) TradeStatus status){
        if (status != null){
            return ResponseEntity.ok(tradeService.findByStatus(status));
        }
        return ResponseEntity.ok(tradeService.getAllTrades());
    }

    @PostMapping("/{tradeId}/exceptions")
    public ResponseEntity<SettlementIssueResponseDto> createSettlementIssue(@RequestBody SettlementIssueRequestDto request, @PathVariable Integer tradeId) {
        SettlementIssueResponseDto settlementIssueResponseDto = settlementIssueService.createSettlementIssue(request, tradeId);
        return ResponseEntity.ok(settlementIssueResponseDto);
    }

}
