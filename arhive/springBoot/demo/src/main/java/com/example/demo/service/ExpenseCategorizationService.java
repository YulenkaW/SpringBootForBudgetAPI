package com.example.demo.service;

import com.example.demo.model.Expense;
import com.example.demo.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpenseCategorizationService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Map<String, List<Expense>> categorizeExpensesByType(String budgetId) {
        List<Expense> expenses = expenseRepository.findByBudgetId(budgetId);
        return expenses.stream().collect(Collectors.groupingBy(Expense::getCategory));
    }
}
