package com.noemiroldan.tradetrackerapi.dto.response;

import com.noemiroldan.tradetrackerapi.enums.AssetType;
import com.noemiroldan.tradetrackerapi.enums.Currency;
import com.noemiroldan.tradetrackerapi.enums.TradeStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TradeResponseDto {
    Integer tradeId;
    AssetType assetType;
    BigDecimal amount;
    Currency currency;
    TradeStatus status;
    Integer counterpartyId;
    String counterpartyName;
}
