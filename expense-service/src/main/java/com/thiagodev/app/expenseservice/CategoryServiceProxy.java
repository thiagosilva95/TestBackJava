package com.thiagodev.app.expenseservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.thiagodev.app.expenseservice.controller.dto.CategoryDTO;

@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "category-service")
public interface CategoryServiceProxy {

	@GetMapping("/category-service/api/v1/categories/{description}")
	public CategoryDTO findCategorySuggestionByDescription(@PathVariable("description") String description);

	@GetMapping("/category-service/api/v1/categories/{id}")
	public CategoryDTO findCategoryById(@PathVariable("id") Long id);
}
