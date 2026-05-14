import api from './api';
import type { SettlementIssueResponse } from '../types/settlementIssue.types';

export const getUnresolvedExceptions = async () => {
    const response = await api.get<SettlementIssueResponse[]>('/exceptions?resolved=false');
    return response.data;
}

export const resolveSettlementIssue = async (settlementId: number) => {
    const response = await api.patch(`/exceptions/${settlementId}/resolve`);
    return response.data;
}