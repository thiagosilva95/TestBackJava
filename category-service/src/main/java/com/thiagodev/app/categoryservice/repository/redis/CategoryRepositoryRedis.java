package com.thiagodev.app.categoryservice.repository.redis;

import java.util.List;
import java.util.Optional;

import com.thiagodev.app.categoryservice.domain.Category;

public interface CategoryRepositoryRedis {
	
	Category insert(Category category);
	
	List<Category> findCategorySuggestionByDescription(String description);

	Optional<Category> findById(Long id);
	
	Optional<Category> findByDescription(String description);

}
