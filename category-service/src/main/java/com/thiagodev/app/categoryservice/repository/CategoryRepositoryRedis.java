package com.thiagodev.app.categoryservice.repository;

import java.util.List;

import com.thiagodev.app.categoryservice.model.Category;

public interface CategoryRepositoryRedis {
	
	List<Category> findCategorySuggestionByDescription(String description);

}
