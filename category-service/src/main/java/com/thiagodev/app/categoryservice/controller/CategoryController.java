package com.thiagodev.app.categoryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.thiagodev.app.categoryservice.controller.dto.CategoryDTO;
import com.thiagodev.app.categoryservice.converter.CategoryConverter;
import com.thiagodev.app.categoryservice.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService service;

	@GetMapping("/api/v1/categories/{description}")
	public ResponseEntity<List<CategoryDTO>> findCategorySuggestionByDescription(@PathVariable final String description){
		final List<CategoryDTO> result = CategoryConverter.toListDTO(service.findCategorySuggestionByDescription(description));
		return new ResponseEntity<List<CategoryDTO>>(result, HttpStatus.OK);
	}

}