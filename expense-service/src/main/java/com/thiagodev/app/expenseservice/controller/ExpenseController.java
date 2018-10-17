package com.thiagodev.app.expenseservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thiagodev.app.expenseservice.controller.dto.CategoryDTO;
import com.thiagodev.app.expenseservice.controller.dto.ExpenseDTO;
import com.thiagodev.app.expenseservice.converter.ExpenseConverter;
import com.thiagodev.app.expenseservice.model.Expense;
import com.thiagodev.app.expenseservice.service.ExpenseService;

@RestController
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@PostMapping("/api/v1/expenses")
	public ResponseEntity<ExpenseDTO> insert(@Valid @RequestBody final ExpenseDTO dto){
		final ExpenseDTO result= ExpenseConverter.toDTO(expenseService.insert(ExpenseConverter.fromDTO(dto)));
		return new ResponseEntity<ExpenseDTO>(result, HttpStatus.OK);
	}

	@PutMapping("/api/v1/expenses")
	public ResponseEntity<ExpenseDTO> update(@Valid @RequestBody final ExpenseDTO dto){
		final ExpenseDTO result = ExpenseConverter.toDTO(expenseService.update(ExpenseConverter.fromDTO(dto)));
		return new ResponseEntity<ExpenseDTO>(result, HttpStatus.OK);
	}
	
	@PutMapping("/api/v1/expenses/{idExpense}/category")
	public ResponseEntity<ExpenseDTO> update(@PathVariable final Long idExpense, @RequestBody final CategoryDTO categoryDTO){
		final ExpenseDTO result = ExpenseConverter.toDTO(expenseService.updateCategory(idExpense, categoryDTO));
		return new ResponseEntity<ExpenseDTO>(result, HttpStatus.OK);
	}

	@GetMapping("/api/v1/expenses/{userCode}")
	public ResponseEntity<Page<ExpenseDTO>> findExpensesByUserCode(@PathVariable final Long userCode, final Pageable pageable){
		final Page<Expense> page = expenseService.findExpensesByUserId(userCode, pageable);
		final Page<ExpenseDTO> result=  new PageImpl<>(ExpenseConverter.toListDTO(page.getContent()), page.getPageable(), page.getTotalElements());
		return new ResponseEntity<Page<ExpenseDTO>>(result, HttpStatus.OK);
	}

	@GetMapping("/api/v1/expenses")
	public ResponseEntity<Page<ExpenseDTO>> findExpensesByFilter(final ExpenseDTO dto, final Pageable pageable){
		final Page<Expense> page = expenseService.findExpensesByFilter(dto.getDate(), dto.getUserCode(), pageable);
		final Page<ExpenseDTO> result=  new PageImpl<>(ExpenseConverter.toListDTO(page.getContent()), page.getPageable(), page.getTotalElements());
		return new ResponseEntity<Page<ExpenseDTO>>(result, HttpStatus.OK);
	}
}
