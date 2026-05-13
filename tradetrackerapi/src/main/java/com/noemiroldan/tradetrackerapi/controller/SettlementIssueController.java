package com.noemiroldan.tradetrackerapi.controller;

import com.noemiroldan.tradetrackerapi.dto.response.SettlementIssueResponseDto;
import com.noemiroldan.tradetrackerapi.service.SettlementIssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exceptions")
@RequiredArgsConstructor
public class SettlementIssueController {
    public final SettlementIssueService settlementIssueService;

    @GetMapping
    public ResponseEntity<List<SettlementIssueResponseDto>> findByResolved(@RequestParam Boolean resolved) {
        return ResponseEntity.ok(settlementIssueService.findByResolved(resolved));
    }

    @PatchMapping("/{settlementIssueId}/resolve")
    public ResponseEntity<SettlementIssueResponseDto> resolveSettlementIssue(@PathVariable Integer settlementIssueId) {
        SettlementIssueResponseDto responseDto = settlementIssueService.resolveSettlementIssue(settlementIssueId);
        return ResponseEntity.ok(responseDto);
    }
}
