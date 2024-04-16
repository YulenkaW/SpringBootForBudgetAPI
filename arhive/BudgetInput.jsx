import React, { useEffect, useState } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { getBudget, postBudget } from './helpers/apiHelpers';



function BudgetInput() {
    // store the budget amount
    const [budget, setBudget] = useState(0);

    
    useEffect(() => {
        handleGetBudget();
    }, []);
    

    const handleGetBudget = async () => {
        try {
            // Fetch budget from backend
            const response = await getBudget();
            if (response && response.data) {
                setBudget(response.data.budget);
            }
        } catch (error) {
            console.error('Error getting budget:', error);
        }
    };

    const [, updateState] = React.useState();
    const forceUpdate = React.useCallback(() => updateState({}), []);
    const handleBudgetChange = (e) => {
        setBudget(e.target.value);
    };

    //Form submission
        const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            sessionStorage.setItem("budget", budget);
            sessionStorage.setItem("balance", budget);
            sessionStorage.setItem("Food", 0);
            sessionStorage.setItem("Transportation", 0);
            sessionStorage.setItem("Entertainment", 0);
            sessionStorage.setItem("Utilities", 0);
            sessionStorage.setItem("Others", 0);
            forceUpdate();            
            // Send budget to backend
            await postBudget({ budget: budget });
            // Show notification
            toast.success(`Your budget is set to: $${budget}`, { position: toast.POSITION.TOP_CENTER });
                // Reset input field
                setBudget(0);
            } catch (error) {
                console.error('Error posting budget:', error);
            }
        };


    return (
        <div>
            <h2>Enter Your Monthly Budget</h2>
            <form onSubmit={handleSubmit}>
                <label htmlFor="budgetInput">Monthly Budget:</label>
                <input
                    type="number"
                    id="budgetInput"
                    value={budget}
                    onChange={handleBudgetChange}
                    placeholder="Enter your budget"
                />
                <button type="submit">Set Budget</button>
            </form>
            <p>Your current budget is: ${sessionStorage.getItem("budget")}</p>
            <ToastContainer />{/* Notification window */}
        </div>
    );
}
export default BudgetInput;

