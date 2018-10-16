package com.thiagodev.app.expenseservice.controller.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ExpenseDTO {

	private Long id;
	private String description;
	private double value;
	private Long userCode;
	@DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime date;
	private Integer version;
}
