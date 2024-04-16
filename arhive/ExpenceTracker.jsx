import { useEffect, useState } from 'react';

const ExpenseTracker = () => {
    const [expenses, setExpenses ] = useState([]);
    useEffect(() => {
      
    }, [expenses]);

    const handleGetExpense = () => {
        
    }
    const fetchBudget = async () => {
    try {
        const response = await fetch('http://localhost:3000/api/expenses');
        const data = await response;
        console.log('budget', data, response)
        
    } catch (error) {
        console.error('Error fetching budgets:', error);
    }
};
    return (
        <ul>
            {expenses.map(expense => (
                <li key={expense.id}>
                    {expense}
                </li>
            ))}
        </ul>
    ); 
}
export default ExpenseTracker;