import axios from "axios";



const config = {
    headers: {
        'Content-Type': 'application/json'
    }
}

export const getBudget = async () =>{
    const url = `http://localhost:3000/api/budget`
    const response = await axios.get(url, config);
    return response;
}

export const postBudget = async (budget) => {
    const url = `http://localhost:3000/api/budget`;
    const body = { budget }; 
    const response = await axios.post(url, body, config);
    return response;
}

export const getExpense = async () => {
    const url = `http://localhost:3000/api/expenses`
    const response = await axios.get(url, config);
    console.log('res', response)
    return response;
}

export const postExpense = async (data) => {
    const url = `http://localhost:3000/api/expense`;
    const body = data
    const response = await axios.post(url, body, config);
    return response;
}