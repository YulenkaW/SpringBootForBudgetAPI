package com.example.demo.service;

import com.example.demo.model.Budget;
import com.example.demo.model.Expense;
import com.example.demo.repository.BudgetRepository;
import com.example.demo.repository.ExpenseRepository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class MonthlyReportService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    //@Autowired
    //private BudgetUtilizationService budgetUtilizationService;

    @Autowired
    private ExpenseCategorizationService expenseCategorizationService;


    

    public MonthlyReport generateReport(String budgetId) {
        Budget budget = budgetRepository.findById(budgetId).orElse(null);
        List<Expense> expenses = expenseRepository.findByBudgetId(budgetId);
        double totalSpent = expenses.stream().mapToDouble(Expense::getAmount).sum();
        double budgetAmount = (budget != null) ? budget.getAmount() : 0;
        double remainingBudget = budgetAmount - totalSpent;
        Map<String, List<Expense>> categorizedExpenses = expenseCategorizationService.categorizeExpensesByType(budgetId);

        MonthlyReport report = new MonthlyReport();
        report.setBudgetAmount(budgetAmount);
        report.setTotalSpent(totalSpent);
        report.setRemainingBudget(remainingBudget);
        report.setCategorizedExpenses(categorizedExpenses);

        // Extend this to include other relevant details in the report

        return report;
    }

    public static class MonthlyReport {
        private double budgetAmount;
        private double totalSpent;
        private double remainingBudget;
        private Map<String, List<Expense>> categorizedExpenses;


        
        public double getBudgetAmount() {
            return budgetAmount;
        }
        public void setBudgetAmount(double budgetAmount) {
            this.budgetAmount = budgetAmount;
        }
        public double getTotalSpent() {
            return totalSpent;
        }
        public void setTotalSpent(double totalSpent) {
            this.totalSpent = totalSpent;
        }
        public double getRemainingBudget() {
            return remainingBudget;
        }
        public void setRemainingBudget(double remainingBudget) {
            this.remainingBudget = remainingBudget;
        }
        public Map<String, List<Expense>> getCategorizedExpenses() {
            return categorizedExpenses;
        }
        public void setCategorizedExpenses(Map<String, List<Expense>> categorizedExpenses) {
            this.categorizedExpenses = categorizedExpenses;
        }
    }


    
}

