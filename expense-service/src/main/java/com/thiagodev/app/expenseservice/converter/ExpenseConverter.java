package com.thiagodev.app.expenseservice.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.thiagodev.app.expenseservice.controller.dto.CategoryDTO;
import com.thiagodev.app.expenseservice.controller.dto.ExpenseDTO;
import com.thiagodev.app.expenseservice.model.Category;
import com.thiagodev.app.expenseservice.model.Expense;

public class ExpenseConverter {

	public static Expense fromDTO(final ExpenseDTO dto) {
		final Expense expense = new Expense();
		
		expense.setId(dto.getId());
		expense.setDescription(dto.getDescription());
		expense.setUserId(dto.getUserCode());
		expense.setExpenseDate(dto.getDate());
		expense.setValue(dto.getValue());
		expense.setVersion(dto.getVersion());
		if (dto.getCategory() != null) {
			Category category = new Category();
			category.setId(dto.getCategory().getId());
			category.setDescription(dto.getCategory().getDescription());
			expense.setCategory(category);
		}
		return expense;
	}

	public static ExpenseDTO toDTO(final Expense model) {
		final ExpenseDTO expenseDTO = new ExpenseDTO();
		expenseDTO.setId(model.getId());
		expenseDTO.setDescription(model.getDescription());
		expenseDTO.setUserCode(model.getUserId());
		expenseDTO.setDate(model.getExpenseDate());
		expenseDTO.setValue(model.getValue());
		expenseDTO.setVersion(model.getVersion());
		
		if (model.getCategory() != null) {
			CategoryDTO category = new CategoryDTO();
			category.setId(model.getCategory().getId());
			category.setDescription(model.getCategory().getDescription());
			expenseDTO.setCategory(category);
		}
		
		return expenseDTO;
	}

	public static List<ExpenseDTO> toListDTO(final List<Expense> listModel) {
		return listModel.stream().map(ExpenseConverter::toDTO).collect(Collectors.toList());
	}
	
}
