package com.thiagodev.app.categoryservice.objectfactory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.thiagodev.app.categoryservice.controller.dto.CategoryDTO;
import com.thiagodev.app.categoryservice.domain.Category;

@Component
public class CategoryMother {

	public static final String DESCRIPTION_MODEL_TEST = "Description Category";
	public static final String DESCRIPTION_DTO_TEST = "Description DTO";

	public static Category getCategoryModelWithIdPattern() {
		final Category category = new Category();
		category.setId(1L);
		category.setDescription(DESCRIPTION_MODEL_TEST);
		return category;
	}

	public static Category getCategoryModelwithoutIdPattern() {
		final Category category = new Category();
		category.setDescription(DESCRIPTION_MODEL_TEST);
		return category;
	}

	public static CategoryDTO getCategoryDTOPattern() {
		final CategoryDTO category = new CategoryDTO();
		category.setCode(1L);
		category.setDescription(DESCRIPTION_DTO_TEST);
		return category;
	}

	public static List<Category> getListCategoryModelPattern() {
		final List<Category> list = new ArrayList<>();
		list.add(getCategoryModelWithIdPattern());
		return list;
	}

	public static List<CategoryDTO> getListCategoryDTOPattern() {
		final List<CategoryDTO> list = new ArrayList<>();
		list.add(getCategoryDTOPattern());
		return list;
	}
}
