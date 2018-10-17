package com.thiagodev.app.expenseservice.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thiagodev.app.expenseservice.CategoryServiceProxy;
import com.thiagodev.app.expenseservice.controller.dto.CategoryDTO;
import com.thiagodev.app.expenseservice.exception.ExpenseNotFoundException;
import com.thiagodev.app.expenseservice.exception.OptimisticLockException;
import com.thiagodev.app.expenseservice.model.Category;
import com.thiagodev.app.expenseservice.model.Expense;
import com.thiagodev.app.expenseservice.repository.CategoryRepository;
import com.thiagodev.app.expenseservice.repository.ExpenseRepository;
import com.thiagodev.app.expenseservice.repository.redis.CategoryRepositoryRedis;

@Service
public class ExpenseService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryServiceProxy categoryProxy;

	@Autowired
	private CategoryRepositoryRedis categoryRepositoryRedis;
	
	
	private static final String OPTIMISTIC_LOCK = "The Expense was update by another transaction.";
	private static final int SECOND_5 = 5;
	private static final int SECOND_59 = 59;
	private static final int MINUTES_59 = 59;
	private static final int HOURS_23 = 23;


	@Transactional
	public Expense insert(final Expense expense) {
		if (expense.getCategory() == null) {
			expense.setCategory(categorizeExpenses(expense.getDescription()));	
		}
		return expenseRepository.save(expense);
	}

	@Transactional
	public Expense update(final Expense expense) {
		validateLockOptimistic(expense);
		return expenseRepository.save(expense);
	}
	
	public Expense findById(Long id) {
		Optional<Expense> expenseOptional = expenseRepository.findById(id);
		
		if (expenseOptional.isPresent()) {
			return expenseOptional.get();
		} else {
			throw new ExpenseNotFoundException("Expense with code " + id + "  not found!");
		}
	}

	public Page<Expense> findExpensesByUserId(final Long userCode, final Pageable pageable) {
		final Page<Expense> expenses = expenseRepository.findByUserIdAndExpenseDateBefore(userCode,
				getLocalDateTimeMinus5Seconds(), pageable);
		if (expenses.getContent().isEmpty()) {
			throw new ExpenseNotFoundException("Expenses not found for user with code: " + userCode);
		}
		return new PageImpl<>(expenses.getContent(), pageable, expenses.getTotalElements());
	}

	public Page<Expense> findExpensesByFilter(final LocalDate date, final Long userCode, final Pageable pageable) {
		final Page<Expense> expenses = expenseRepository.findByUserIdAndExpenseDateEquals(userCode,
				Date.valueOf(date), pageable);
		if (expenses.getContent().isEmpty()) {
			throw new ExpenseNotFoundException(
					"Expenses not found for user with code: " + userCode + " and date: " + date);
		}
		return new PageImpl<>(expenses.getContent(), pageable, expenses.getTotalElements());
	}

	@Transactional
	public Category categorizeExpenses(final String description) {
		Optional<Expense> expenseOptional = findExpenseCategoryzedByDescription(description);
		if (expenseOptional.isPresent()) {
			return expenseOptional.get().getCategory();
		}
		return null;
	}

	private Optional<Expense> findExpenseCategoryzedByDescription(String description) {
		List<Expense> expensesCategorized = expenseRepository.findExpensesCategoryzedByDescription(description);
		
		return expensesCategorized.isEmpty() ? Optional.empty() : Optional.of(expensesCategorized.get(0));
	}

	private void validateLockOptimistic(final Expense expense) {
		final Optional<Expense> result = expenseRepository.findById(expense.getId());
		if (result.isPresent() && result.get().getVersion().compareTo(expense.getVersion()) != 0) {
			throw new OptimisticLockException(OPTIMISTIC_LOCK);
		}
	}

	public static LocalDateTime getLocalDateTimeMinus5Seconds() {
		return LocalDateTime.now().minusSeconds(SECOND_5);
	}

	public static LocalDateTime getLocalDateTimeStartTime(final LocalDateTime expenseDate) {
		return LocalDateTime.of(expenseDate.getYear(), expenseDate.getMonth(), expenseDate.getDayOfMonth(), 0, 0, 0);
	}

	public static LocalDateTime getLocalDateEndTime(final LocalDateTime expenseDate) {
		return LocalDateTime.of(expenseDate.getYear(), expenseDate.getMonth(), expenseDate.getDayOfMonth(), HOURS_23, MINUTES_59, SECOND_59);
	}
	
	/*private Category getCategoryById(final Long id) {
		CategoryDTO response = categoryProxy.findCategoryById(id);
		
		logger.info("{}", response);
		
		return new Category(response.getId(), response.getDescription());
	}*/
	
}
