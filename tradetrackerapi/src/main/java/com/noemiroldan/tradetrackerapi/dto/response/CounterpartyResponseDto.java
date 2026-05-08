package com.noemiroldan.tradetrackerapi.dto.response;

import com.noemiroldan.tradetrackerapi.enums.Country;
import com.noemiroldan.tradetrackerapi.enums.RiskRating;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CounterpartyResponseDto {
    Integer counterpartyId;
    String name;
    Country country;
    RiskRating riskRating;
}
