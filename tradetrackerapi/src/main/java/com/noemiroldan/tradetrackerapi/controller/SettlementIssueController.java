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

    @PatchMapping("/{id}/resolve")
    public ResponseEntity<SettlementIssueResponseDto> updateSettlementIssue(@PathVariable Integer id) {
        SettlementIssueResponseDto responseDto = settlementIssueService.updateSettlementIssue(id);
        return ResponseEntity.ok(responseDto);
    }
}
