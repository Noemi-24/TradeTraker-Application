export interface Trade {
    tradeId: number;
    counterparty: number;
    assetType: AssetType;
    tradeDate: Date;
    settlementDate: Date;
    amount: number;
    currency: Currency;
    status: TradeStatus;
}

export type AssetType = 'EQUITY' | 'BOND' | 'FOREX' | 'DERIVATIVE' ;
export type Currency = 'USD' | 'GBP' | 'EUR'  | 'INR' ;
export type TradeStatus = 'PENDING' | 'SETTLED' | 'FAILED';

export interface TradeRequest {
    assetType: AssetType;
    tradeDate: Date;
    settlementDate: Date;
    amount: number;
    currency: Currency;
    counterparty: number;
}

export interface TradeResponse {
    tradeId: number;
    assetType: AssetType;
    amount: number;
    currency: Currency;
    status: TradeStatus;
    counterparty: number;
    counterpartyName: string;
}