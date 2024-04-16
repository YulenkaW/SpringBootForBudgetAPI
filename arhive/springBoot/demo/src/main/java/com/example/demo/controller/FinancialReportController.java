package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Expense;
import com.example.demo.service.BudgetUtilizationService;
import com.example.demo.service.ExpenseCategorizationService;

@RestController
public class FinancialReportController {

    @Autowired
    private BudgetUtilizationService budgetUtilizationService;

    @Autowired
    private ExpenseCategorizationService expenseCategorizationService;

    @GetMapping("/api/reports/{budgetId}")
    public String generateReport(@PathVariable String budgetId) {
        double remainingBudget = budgetUtilizationService.calculateRemainingBudget(budgetId);
        Map<String, List<Expense>> categorizedExpenses = expenseCategorizationService
                .categorizeExpensesByType(budgetId);
       StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Financial Report for Budget ID ").append(budgetId).append(":\n");
        reportBuilder.append("Remaining Budget: $").append(remainingBudget).append("\n");
        reportBuilder.append("Categorized Expenses:\n");

        for (Map.Entry<String, List<Expense>> entry : categorizedExpenses.entrySet()) {
            String category = entry.getKey();
            List<Expense> expenses = entry.getValue();
            double totalAmount = expenses.stream().mapToDouble(Expense::getAmount).sum();
            reportBuilder.append(" - Category: ").append(category).append(", Total Spent: $").append(totalAmount).append("\n");
            
            // Optionally, listing individual expenses within each category
            for (Expense expense : expenses) {
                reportBuilder.append("   - ").append(expense.getDescription()).append(": $").append(expense.getAmount()).append("\n");
            }
        }

        return reportBuilder.toString();
    }
}
