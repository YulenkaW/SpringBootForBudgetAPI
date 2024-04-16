package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.repository.SalaryRepository;

@Document
public class Salary implements SalaryRepository {
        @Id
        private String id;
        private double salary;
        

        public Salary() {

        }

        @Override
        public String setId() {
            return id;
        }

        @Override
        public void getID (String id) {
            this.id=id;
        }

        @Override
        public double setSalary() {
            return salary;
        }
        

        @Override
        public void getSalary(double salary) {
            this.salary = salary;
        }

    }


