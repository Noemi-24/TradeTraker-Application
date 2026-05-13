package com.noemiroldan.tradetrackerapi.service;


import com.noemiroldan.tradetrackerapi.dto.request.SettlementIssueRequestDto;
import com.noemiroldan.tradetrackerapi.dto.response.SettlementIssueResponseDto;

import java.util.List;

public interface SettlementIssueService {
    SettlementIssueResponseDto resolveSettlementIssue(Integer settlementIssueId);
    List<SettlementIssueResponseDto> findByResolved(Boolean resolved);
    SettlementIssueResponseDto createSettlementIssue(SettlementIssueRequestDto request, Integer tradeId);
}
