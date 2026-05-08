package com.noemiroldan.tradetrackerapi.entity;

import com.noemiroldan.tradetrackerapi.enums.ReasonCode;
import com.noemiroldan.tradetrackerapi.enums.Severity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
@Entity
@Table(name = "settlement_issue")
public class SettlementIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "settlement_id")
    Integer settlementId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trade_id")
    Trade trade;

    @Enumerated(EnumType.STRING)
    @Column(name = "reason_code")
    ReasonCode reasonCode;

    String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "severity")
    Severity severity;

    @Column(name = "created_at")
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(name = "resolved")
    Boolean resolved;
}
