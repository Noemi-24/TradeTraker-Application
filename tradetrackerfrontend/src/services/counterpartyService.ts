import api from './api';
import type { SettlementIssueResponse } from '../types/settlementIssue.types';

export const findAllByResolvedStatus = async (resolved: boolean) => {
    const response = await api.get<SettlementIssueResponse[]>(`/exceptions?resolved=${resolved}`);
    return response.data;
}

export const resolveSttlementIssue = async (settlementId: number) => {
    const response = await api.patch(`/exceptions/${settlementId}/resolve`);
    return response.data;
}