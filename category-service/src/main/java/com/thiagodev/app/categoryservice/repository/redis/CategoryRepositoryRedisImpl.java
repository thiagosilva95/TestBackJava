package com.thiagodev.app.categoryservice.repository.redis;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.thiagodev.app.categoryservice.domain.Category;
import com.thiagodev.app.categoryservice.support.RedisKeysHelper;

@Repository
public class CategoryRepositoryRedisImpl implements CategoryRepositoryRedis {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public Category insert(final Category category) {
		final String categoryKey = RedisKeysHelper.generateCategoriesKey(category.getId());
		redisTemplate.opsForValue().set(categoryKey, RedisKeysHelper.deserializableToString(category));
		return RedisKeysHelper.serializableToObject(redisTemplate.opsForValue().get(categoryKey), Category.class);
	}
	
	@Override
	public List<Category> findCategorySuggestionByDescription(final String description) {
		final List<String> listJson= redisTemplate.opsForValue().multiGet(redisTemplate.keys("categories:*"));
		final List<Category> listCategory= listJson.stream().map(json -> RedisKeysHelper.serializableToObject(json, Category.class)).collect(Collectors.toList());
		return listCategory.stream().filter(obj -> obj.getDescription().contains(description)).collect(Collectors.toList());
	}

	@Override
	public Optional<Category> findById(Long id) {
		final List<String> listJson= redisTemplate.opsForValue().multiGet(redisTemplate.keys("categories:*"));
		final List<Category> listCategory= listJson.stream().map(json -> RedisKeysHelper.serializableToObject(json, Category.class)).collect(Collectors.toList());
		Optional<Category> findFirst = listCategory.stream().filter(obj -> obj.getId().equals(id)).findFirst();
		return findFirst;
		
	}
	
	public Optional<Category> findByDescription(String description) {
		final List<String> listJson= redisTemplate.opsForValue().multiGet(redisTemplate.keys("categories:*"));
		final List<Category> listCategory= listJson.stream().map(json -> RedisKeysHelper.serializableToObject(json, Category.class)).collect(Collectors.toList());
		return listCategory.stream().filter(obj -> obj.getDescription().contains(description)).findFirst();
	}
}
