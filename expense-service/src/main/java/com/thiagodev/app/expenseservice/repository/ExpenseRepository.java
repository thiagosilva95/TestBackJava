package com.thiagodev.app.expenseservice.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thiagodev.app.expenseservice.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	Page<Expense> findByUserIdAndExpenseDateBefore(Long idUser, LocalDateTime expenseDate, Pageable pageable);

	Page<Expense> findByUserIdAndExpenseDateBetween(Long idUser, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

	List<Expense> findExpensesCategoryzedByDescription(String description);
}
