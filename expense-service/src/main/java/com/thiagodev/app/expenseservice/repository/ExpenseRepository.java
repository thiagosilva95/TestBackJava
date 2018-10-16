package com.thiagodev.app.expenseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiagodev.app.expenseservice.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
