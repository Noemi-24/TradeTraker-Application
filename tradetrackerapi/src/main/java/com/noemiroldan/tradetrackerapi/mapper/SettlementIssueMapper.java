package com.noemiroldan.tradetrackerapi.mapper;

import com.noemiroldan.tradetrackerapi.dto.request.SettlementIssueRequestDto;
import com.noemiroldan.tradetrackerapi.dto.response.SettlementIssueResponseDto;
import com.noemiroldan.tradetrackerapi.entity.SettlementIssue;
import com.noemiroldan.tradetrackerapi.entity.Trade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SettlementIssueMapper {
    public SettlementIssueResponseDto toSettlementIssueResponseDto(SettlementIssue settlementIssue) {
        SettlementIssueResponseDto settlementIssueResponseDto = new SettlementIssueResponseDto();

        settlementIssueResponseDto.setSettlementId(settlementIssue.getSettlementId());
        settlementIssueResponseDto.setTradeId(settlementIssue.getTrade().getTradeId());
        settlementIssueResponseDto.setReasonCode(settlementIssue.getReasonCode());
        settlementIssueResponseDto.setDescription(settlementIssue.getDescription());
        settlementIssueResponseDto.setSeverity(settlementIssue.getSeverity());
        settlementIssueResponseDto.setCreatedAt(settlementIssue.getCreatedAt());
        settlementIssueResponseDto.setResolved(settlementIssue.getResolved());

        return settlementIssueResponseDto;
    }

    public SettlementIssue toSettlementIssue(SettlementIssueRequestDto request, Trade trade) {
        SettlementIssue settlementIssue = new SettlementIssue();

        settlementIssue.setTrade(trade);
        settlementIssue.setReasonCode(request.getReasonCode());
        settlementIssue.setDescription(request.getDescription());
        settlementIssue.setSeverity(request.getSeverity());

        return settlementIssue;
    }
}
