package com.noemiroldan.tradetrackerapi.entity;

import com.noemiroldan.tradetrackerapi.enums.Country;
import com.noemiroldan.tradetrackerapi.enums.RiskRating;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
@Entity
@Table(name = "counterparty")
public class Counterparty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "counterparty_id")
    Integer counterpartyId;

    @Column(name = "name")
    String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "country")
    Country country;

    @NotNull(message = "Risk rating is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "risk_rating")
    RiskRating riskRating;
}
