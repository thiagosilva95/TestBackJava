package com.thiagodev.app.categoryservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thiagodev.app.categoryservice.controller.dto.CategoryDTO;
import com.thiagodev.app.categoryservice.converter.CategoryConverter;
import com.thiagodev.app.categoryservice.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/api/v1/categories")
	public ResponseEntity<CategoryDTO> insert(@Valid @RequestBody final CategoryDTO dto){
		final CategoryDTO result= CategoryConverter.toDTO(categoryService.insert(CategoryConverter.fromDTO(dto)));
		return new ResponseEntity<CategoryDTO>(result, HttpStatus.CREATED);
	}

	@GetMapping("/api/v1/categories")
	public ResponseEntity<List<CategoryDTO>> findCategorySuggestionByDescription(final String description){
		final List<CategoryDTO> result = CategoryConverter.toListDTO(categoryService.findCategorySuggestionByDescription(description));
		return new ResponseEntity<List<CategoryDTO>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/categories/{id}")
	public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable final Long id){
		final CategoryDTO result = CategoryConverter.toDTO(categoryService.findCategoryById(id));
		return new ResponseEntity<CategoryDTO>(result, HttpStatus.OK);
	}
	
	

}
