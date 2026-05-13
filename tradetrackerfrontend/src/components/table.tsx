import type { ReactNode} from 'react';

export interface Column<T> {
    header: string;
    render: (item: T) => ReactNode;
}

interface TableProps<T> {
    data: T[];
    columns: Column<T>[];
    rowKey: (item: T) => string | number;
}

export const Table = <T, >({ data, columns, rowKey }: TableProps<T>) => {
    return(
        <div className="w-full overflow-x-auto rounded-xl border border-gray-200 bg-white shadow-sm">
            <table 
                role="table" 
                className="min-w-full text-sm text-left">
                <thead className="bg-gray-100 text-gray-700">
                    <tr>
                        {columns.map((col) => (
                        <th key={col.header} 
                        className="px-4 sm:px-6 py-3 font-semibold whitespace-nowrap" >{col.header}</th>
                        ))}
                    </tr>
                </thead>
                <tbody>
                    {data.map((item, rowIndex) => (
                    <tr key={rowKey(item)}
                        className={`border-b border-gray-200 ${
                            rowIndex % 2 === 0 
                            ? 'bg-white' 
                            : 'bg-gray-50'
                        }
                        transition-colors duration-150 hover:bg-blue-50`}>
                        {columns.map((col) => (
                        <td key={col.header} 
                            className="px-4 sm:px-6 py-4 text-gray-700 whitespace-nowrap">
                            {col.render(item)}
                        </td>
                        ))}
                    </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};