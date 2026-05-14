import api from './api';
import type { TradeRequest, TradeResponse, Trade } from '../types/trade.types';
import type { SettlementIssue } from '../types/settlementIssue.types';

export const getTradeById = async (id: number) => {
    const response = await api.get<TradeResponse>(`/trades/${id}`);
    return response.data;
}

export const getAllTrades = async () => {
    const response = await api.get<TradeResponse[]>('/trades');
    return response.data;
}

export const createTrade = async (data: TradeRequest) : Promise<Trade> => {
    const response = await api.post<Trade>('/trades', data);
    return response.data;
}

export const createSettlementIssue = async (tradeId: number, data: SettlementIssue) => {
    const response = await api.post(`/trades/${tradeId}/exceptions`, data);
    return response.data;
}