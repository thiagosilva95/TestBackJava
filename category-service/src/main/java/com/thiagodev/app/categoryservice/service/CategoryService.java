package com.thiagodev.app.categoryservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagodev.app.categoryservice.domain.Category;
import com.thiagodev.app.categoryservice.repository.CategoryRepository;
import com.thiagodev.app.categoryservice.repository.redis.CategoryRepositoryRedis;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryRepositoryRedis categoryRepositoryRedis;
	
	public Category insert(Category category) {
		return new Category().insert(categoryRepository.save(category));
	}
	
	public List<Category> findCategorySuggestionByDescription(final String description) {
		return new Category(categoryRepository, categoryRepositoryRedis).findCategorySuggestionByDescription(description);
	}
	
	public Category findCategoryById(Long id) {
		return new Category().findCategoryById(id);
	}

}
