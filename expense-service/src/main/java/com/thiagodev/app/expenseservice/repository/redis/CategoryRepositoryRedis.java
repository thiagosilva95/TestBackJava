package  com.thiagodev.app.expenseservice.repository.redis;

import java.util.Optional;

import com.thiagodev.app.expenseservice.model.Category;

public interface CategoryRepositoryRedis {

	Category insert(Category category);

	Optional<Category> findByDescriptionEqualsIgnoreCase(String description);
}
