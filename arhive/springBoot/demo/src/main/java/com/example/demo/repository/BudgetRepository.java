package com.example.demo.repository;


import com.example.demo.model.Budget;

import com.example.demo.repository.BudgetRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BudgetRepository extends MongoRepository<Budget, String> {
    // Define custom query methods here
}