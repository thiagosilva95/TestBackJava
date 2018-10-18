package com.thiagodev.app.categoryservice.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.thiagodev.app.categoryservice.exception.CategoryNotFoundException;
import com.thiagodev.app.categoryservice.repository.CategoryRepository;
import com.thiagodev.app.categoryservice.repository.redis.CategoryRepositoryRedis;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_category", precision = 12, scale = 0)
	private Long id;

	@Column(nullable = false, length = 200)
	private String description;
	
	@Transient @Getter(value = AccessLevel.NONE) @Setter(value = AccessLevel.NONE)
	private CategoryRepository categoryRepository;
	@Transient @Getter(value = AccessLevel.NONE) @Setter(value = AccessLevel.NONE)
	private CategoryRepositoryRedis categoryRepositoryRedis;


	public Category(final CategoryRepository categoryRepository, final CategoryRepositoryRedis categoryRepositoryRedis){
		this.categoryRepository = categoryRepository;
		this.categoryRepositoryRedis = categoryRepositoryRedis;
	}
	
	public Category insert(Category category) {
		return categoryRepositoryRedis.insert(categoryRepository.save(category));
	}
	
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

	public Category findCategoryById(Long id) {
		Optional<Category> optionalCategory = categoryRepositoryRedis.findById(id);
		if(!optionalCategory.isPresent()) {
			 optionalCategory = categoryRepository.findById(id);
		}
		if(!optionalCategory.isPresent()) {
			throw new CategoryNotFoundException("Categories not found with this id: "+ id);
		}
		return optionalCategory.get();
	}
}
