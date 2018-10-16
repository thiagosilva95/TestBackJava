package com.thiagodev.app.categoryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiagodev.app.categoryservice.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByDescriptionContainingIgnoreCase(String description);

}
