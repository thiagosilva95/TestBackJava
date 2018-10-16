package com.thiagodev.app.expenseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiagodev.app.expenseservice.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	Category findByDescriptionEqualsIgnoreCase(String description);
}
