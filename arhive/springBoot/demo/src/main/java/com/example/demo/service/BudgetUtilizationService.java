package com.example.demo.service;

import com.example.demo.model.Budget;
import com.example.demo.model.Expense;
import com.example.demo.repository.BudgetRepository;
import com.example.demo.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetUtilizationService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    public BudgetUtilization calculateUtilization(String budgetId) {
        double totalBudget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new RuntimeException("Budget not found"))
                .getAmount();
        double totalExpenses = expenseRepository.findByBudgetId(budgetId)
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
        double remaining = totalBudget - totalExpenses;

        return new BudgetUtilization(totalBudget, totalExpenses, remaining);
    }

    public double calculateRemainingBudget(String budgetId) {
        Budget budget = budgetRepository.findById(budgetId).orElseThrow();
        // This method would calculate the remaining budget based on expenses.
        
        return budget.getAmount(); 
    }


    public static class BudgetUtilization {
        private final double totalBudget;
        private final double totalExpenses;
        private final double remaining;

        public BudgetUtilization(double totalBudget, double totalExpenses, double remaining) {
            this.totalBudget = totalBudget;
            this.totalExpenses = totalExpenses;
            this.remaining = remaining;
        }

        // Getters
        public double getTotalBudget() {
            return totalBudget;
        }

        public double getTotalExpenses() {
            return totalExpenses;
        }

        public double getRemaining() {
            return remaining;
        }
        
    }
}
