package com.thiagodev.app.expenseservice.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.thiagodev.app.expenseservice.controller.dto.ExpenseDTO;
import com.thiagodev.app.expenseservice.model.Category;
import com.thiagodev.app.expenseservice.model.Expense;

public class ExpenseConverter {

	public static Expense fromDTO(final ExpenseDTO dto) {
		final Expense expense = new Expense();
		final Category category = new Category();
		expense.setCategory(category);
		expense.setId(dto.getId());
		expense.getCategory().setDescription(dto.getDescription());
		expense.setUserId(dto.getUserCode());
		expense.setExpenseDate(dto.getDate());
		expense.setValue(dto.getValue());
		expense.setVersion(dto.getVersion());
		return expense;
	}

	public static ExpenseDTO toDTO(final Expense model) {
		final ExpenseDTO expenseDTO = new ExpenseDTO();
		expenseDTO.setId(model.getId());
		if (model.getCategory() != null) {
			expenseDTO.setDescription(model.getCategory().getDescription());
		}
		expenseDTO.setUserCode(model.getUserId());
		expenseDTO.setDate(model.getExpenseDate());
		expenseDTO.setValue(model.getValue());
		expenseDTO.setVersion(model.getVersion());
		return expenseDTO;
	}

	public static List<ExpenseDTO> toListDTO(final List<Expense> listModel) {
		return listModel.stream().map(ExpenseConverter::toDTO).collect(Collectors.toList());
	}
	
}
