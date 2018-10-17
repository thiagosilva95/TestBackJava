package com.thiagodev.app.expenseservice.controller.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseFilterDTO {
	
	@DateTimeFormat(pattern ="dd/MM/yyyy")
	private LocalDate date; 
	private Long userCode;
}
