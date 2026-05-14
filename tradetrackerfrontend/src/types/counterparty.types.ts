export interface Counterparty {
    counterpartyId: number;
    name: string;
    country: Country;
    riskRsting: RiskRating;
}

export type Country = 'USA' | 'UK' | 'CANADA' | 'INDIA' | 'FRANCE' | 'GERMANY' |'JAPAN';
export type RiskRating = 'LOW' | 'MEDIUM' | 'HIGH';

export interface CounterpartyRequest {
    name: string;
    country: Country;
    riskRsting: RiskRating;
}

export type CounterpartyResponse = Counterparty;