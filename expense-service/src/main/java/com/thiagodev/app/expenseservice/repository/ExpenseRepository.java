package com.thiagodev.app.expenseservice.repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.thiagodev.app.expenseservice.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	Page<Expense> findByUserIdAndExpenseDateBefore(Long idUser, LocalDateTime expenseDate, Pageable pageable);

	//Page<Expense> findByUserIdAndExpenseDateBetween(Long idUser, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
	@Query("select e from Expense e where e.userId = ?1 and DATE(e.expenseDate) = ?2")
	Page<Expense> findByUserIdAndExpenseDateEquals(Long idUser, Date date, Pageable pageable);
	

	@Query("select e from Expense e where e.category is not null and e.description = ?1")
	List<Expense> findExpensesCategoryzedByDescription(String description);
}
