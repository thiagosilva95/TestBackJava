package com.thiagodev.app.categoryservice.converter;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.thiagodev.app.categoryservice.controller.dto.CategoryDTO;
import com.thiagodev.app.categoryservice.domain.Category;

public class CategoryConverter {
	
	public static CategoryDTO toDTO(final Category model) {
		final CategoryDTO dto = new CategoryDTO();
		dto.setId(model.getId());
		dto.setDescription(model.getDescription());
		return dto;
	}

	public static List<CategoryDTO> toListDTO(final List<Category> listModel) {
		return listModel.stream().map(CategoryConverter::toDTO).collect(Collectors.toList());
	}

	public static Category fromDTO(@Valid CategoryDTO dto) {
		final Category category = new Category();
		category.setId(dto.getId());
		category.setDescription(dto.getDescription());
		return category;
	}
}
