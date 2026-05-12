package com.noemiroldan.tradetrackerapi.dto.response;

import com.noemiroldan.tradetrackerapi.enums.ReasonCode;
import com.noemiroldan.tradetrackerapi.enums.Severity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SettlementIssueResponseDto {
    Integer settlementId;
    Integer tradeId;
    ReasonCode reasonCode;
    String description;
    Severity severity;
    LocalDateTime createdAt;
    Boolean resolved;
}
