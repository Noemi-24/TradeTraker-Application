package com.noemiroldan.tradetrackerapi.dto.request;

import com.noemiroldan.tradetrackerapi.enums.Country;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CounterpartyRequestDto {
    String name;
    Country country;
}
