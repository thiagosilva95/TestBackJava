package com.thiagodev.app.categoryservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagodev.app.categoryservice.exception.CategoryNotFoundException;
import com.thiagodev.app.categoryservice.model.Category;
import com.thiagodev.app.categoryservice.repository.CategoryRepository;
import com.thiagodev.app.categoryservice.repository.redis.CategoryRepositoryRedis;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryRepositoryRedis categoryRepositoryRedis;
	
	public List<Category> findCategorySuggestionByDescription(String description) {
		List<Category> listCategories = categoryRepositoryRedis.findCategorySuggestionByDescription(description);
		if(listCategories.isEmpty()) {
			listCategories = categoryRepository.findByDescriptionContainingIgnoreCase(description);
		}
		if(listCategories.isEmpty()) {
			throw new CategoryNotFoundException("Categories not found with this description: "+ description);
		}
		return listCategories;
	}

}
