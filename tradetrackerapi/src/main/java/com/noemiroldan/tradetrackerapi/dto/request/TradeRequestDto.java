package com.noemiroldan.tradetrackerapi.dto.request;

import com.noemiroldan.tradetrackerapi.enums.AssetType;
import com.noemiroldan.tradetrackerapi.enums.Currency;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TradeRequestDto {
    AssetType assetType;

    @NonNull
    LocalDate tradeDate;

    @NonNull
    LocalDate settlementDate;

    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    BigDecimal amount;

    @NotNull(message = "Currency is required")
    Currency currency;

    Integer counterpartyId;
}
