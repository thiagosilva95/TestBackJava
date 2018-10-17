package com.thiagodev.app.expenseservice.controller.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ExpenseDTO {

	private Long id;
	@NotNull
	private String description;
	@NotNull
	private double value;
	@NotNull
	private Long userCode;
	@NotNull
	@DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime date;
	private Integer version;
	private CategoryDTO category;
}
