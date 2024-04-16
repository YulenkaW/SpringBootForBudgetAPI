package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Budget;
import com.example.demo.repository.BudgetRepository;



@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;

    
    @GetMapping
    public ResponseEntity<List<Budget>> getAllBudgets() {
        List<Budget> budgets = budgetRepository.findAll();
        return ResponseEntity.ok(budgets);
    }

    // method to get a specific budget by ID
    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable String id) {
        return budgetRepository.findById(id)
                .map(budget -> ResponseEntity.ok().body(budget)) // If found, return 200 OK with the budget
                .orElse(ResponseEntity.notFound().build()); // If not found, return 404 Not Found
    }

    @PostMapping
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
        Budget savedBudget = budgetRepository.save(budget);
        return new ResponseEntity<>(savedBudget, HttpStatus.CREATED);
    }
    
 //method to update existing budget 
    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable String id, @RequestBody Budget updatedBudget) {
        return budgetRepository.findById(id)
                .map(budget -> {
                    budget.setAmount(updatedBudget.getAmount());
                    // here to add if we want to update other fields- to create them in setters before that
                    Budget savedBudget = budgetRepository.save(budget);
                    return ResponseEntity.ok(savedBudget);
                })
                .orElse(ResponseEntity.notFound().build());
    }
//delete budget
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable String id) {
        if (budgetRepository.existsById(id)) {
            budgetRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    

}
