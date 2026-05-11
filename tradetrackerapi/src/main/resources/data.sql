-- =========================
-- COUNTERPARTY SEED DATA
-- =========================

INSERT INTO counterparty (
    name,
    country,
    risk_rating
) VALUES
      ('JPMorgan Chase', 'USA', 'LOW'),

      ('Goldman Sachs', 'USA', 'MEDIUM'),

      ('Morgan Stanley', 'UK', 'HIGH');



-- =========================
-- TRADE SEED DATA
-- =========================

INSERT INTO trade (
    asset_type,
    trade_date,
    settlement_date,
    amount,
    currency,
    status,
    counterparty_id
) VALUES
      (
          'EQUITY',
          '2026-05-06',
          '2026-05-08',
          250000.00,
          'USD',
          'PENDING',
          1
      ),

      (
          'FOREX',
          '2026-05-05',
          '2026-05-07',
          500000.00,
          'EUR',
          'FAILED',
          2
      ),

      (
          'BOND',
          '2026-05-01',
          '2026-05-03',
          1000000.00,
          'GBP',
          'SETTLED',
          3
      );



-- =========================
-- SETTLEMENT ISSUE SEED DATA
-- =========================

INSERT INTO settlement_issue (
    trade_id,
    reason_code,
    description,
    severity,
    created_at,
    resolved
) VALUES
      (
          1,
          'INSUFFICIENT_FUNDS',
          'Counterparty account balance was insufficient for settlement.',
          'HIGH',
          '2026-05-06T10:15:00',
          FALSE
      ),

      (
          2,
          'MISSING_REFERENCE_DATA',
          'SWIFT code missing from settlement instructions.',
          'MEDIUM',
          '2026-05-06T11:00:00',
          TRUE
      );