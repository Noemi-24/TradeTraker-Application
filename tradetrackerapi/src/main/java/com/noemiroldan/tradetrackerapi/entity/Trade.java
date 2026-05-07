package com.noemiroldan.tradetrackerapi.entity;

import com.noemiroldan.tradetrackerapi.enums.AssetType;
import com.noemiroldan.tradetrackerapi.enums.Currency;
import com.noemiroldan.tradetrackerapi.enums.TradeStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
@Entity
@Table(name = "trade")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_id")
    Integer tradeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counterparty_id")
    Counterparty counterpartyId;

    @Enumerated(EnumType.STRING)
    @Column(name = "asset_type")
    AssetType assetType;

    @NonNull
    @Column(name = "trade_date")
    LocalDate tradeDate;

    @NonNull
    @Column(name = "settlement_date")
    LocalDate settlementDate;

    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    @Column(name = "amount", precision = 12, scale = 2)
    BigDecimal amount;

    @NotNull(message = "Currency is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    Currency currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    TradeStatus status;
}
