package com.noemiroldan.tradetrackerapi.dto.request;

import com.noemiroldan.tradetrackerapi.enums.ReasonCode;
import com.noemiroldan.tradetrackerapi.enums.Severity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SettlementIssueRequestDto {
    ReasonCode reasonCode;
    String description;
    Severity severity;
}
