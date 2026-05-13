export interface SettlementIssue {
    settlementId: number;
    tradeId: number;
    reasonCode: ReasonCode;
    description: string;
    severity: Severity;
    createdAt: Date;
    resolved: boolean;
}

export type ReasonCode = 'MISSING_REFERENCE_DATA' | 'INSUFFICIENT_FUNDS' | 'INVALID_COUNTERPARTY' | 'DATE_MISMATCH';
export type Severity = 'LOW' | 'MEDIUM' | 'HIGH';

export interface SettlementIssueRequest{
    ReasonCode: ReasonCode;
    Description: string;
    Severity: Severity;
}

export type SettlementIssueResponse = SettlementIssue;