import { useEffect, useState } from 'react';
import { Table, type Column } from '../components/table';
import type { SettlementIssueResponse } from '../types/settlementIssue.types';
import { getUnresolvedExceptions, resolveSettlementIssue } from '../services/settlementIssueService';

function Dashboard() {
    const [unresolvedExceptions, setUnresolvedExceptions] = useState<SettlementIssueResponse[]>([]);
     const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<string | null>("");

    useEffect(() => {
        const fetchUnresolvedExceptions = async () => {
            setLoading(true);
            setError(null);
            try{
                const result = await getUnresolvedExceptions();
                setUnresolvedExceptions(result);
            }catch(error){
                setError(error instanceof Error ? error.message: "Failed to fetch unresolved exceptions. Please try again later.");
            }finally{
                setLoading(false);
            }
        };

        fetchUnresolvedExceptions();
    }, []);

    const columns: Column<SettlementIssueResponse>[] = [
        {
            header: 'Reason Code',
            render:(settlementIssue) => settlementIssue.reasonCode
        },
        {
            header: 'Severity',
            render:(settlementIssue) => settlementIssue.severity
        },
        {
            header: 'Trade ID',
            render:(settlementIssue) => settlementIssue.tradeId
        },
        {
            header: 'Deescription',
            render:(settlementIssue) => settlementIssue.description
        },
        {
            header: 'Created At',
            render:(settlementIssue) => new Date(settlementIssue.createdAt).toLocaleString('en-US', {
                month: 'short',
                day: 'numeric',
                hour: 'numeric',
                minute: '2-digit',
                hour12: true
            }) 
        },
        {
            header: 'Actions',
            render:(settlementIssue) => 
                <button 
                    onClick={() => handleResolveSettlementIssue(settlementIssue.settlementId)}
                    className="bg-gray-200 hover:bg-gray-300 text-gray-800 px-3 py-1.5 rounded-lg text-sm font-medium transition">
                    Resolve
                </button>
        }
    ];    

    const handleResolveSettlementIssue = async (id : number) => {
        await resolveSettlementIssue(id);
        setUnresolvedExceptions(prev => prev.filter(issue => issue.settlementId !== id));
    }

    if (loading) return (
        <div className="w-full max-w-6xl mx-auto px-4 sm:px-6 lg:px-8 py-6 flex justify-center">
            <p className="text-gray-600">Loading unresolved exceptions...</p>
        </div>
    );
        
    if (error) return (
        <div className="flex items-center justify-center min-h-screen">
            <p className="text-red-600">Error: {error}</p>
        </div>
    );

    return(
        <div className="w-full max-w-7xl mx-auto p-4 sm:p-6 lg:p-8">
            <div className='mb-8'>
                <p className="text-xs font-medium uppercase tracking-wide text-blue-600 mb-1">Dashboard</p>
                <h1 className="text-2xl sm:text-3xl font-bold text-gray-900 mb-2 tracking-tight">Welcome back, Team!</h1>
            </div>
            <div className="bg-white rounded-xl shadow-sm p-4">
                <h2 className="text-lg sm:text-xl font-semibold tracking-tight text-gray-900 mb-6">Unresolved Exceptions</h2>
                <Table data={unresolvedExceptions} columns={columns} rowKey={(settlementIssue) => settlementIssue.settlementId}/> 
            </div>
        </div>
    )
}

export default Dashboard;