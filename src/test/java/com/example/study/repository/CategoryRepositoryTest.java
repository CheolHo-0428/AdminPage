package com.example.study.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;
import com.example.study.model.enums.CategoryType;

public class CategoryRepositoryTest extends StudyApplicationTests{
	
	@Autowired
	private CategoryRepository repo;
	
	@Test
	public void create() {
		Category category = new Category();
		
		category.setType(CategoryType.COMPUTER);
		category.setTitle("삼성컴퓨터");
		category.setCreatedAt(LocalDateTime.now());
		category.setCreatedBy("AdminServer");
		
		Category newCategory = repo.save(category);
		
		Assertions.assertNotNull(newCategory);
		Assertions.assertEquals(category.getType(), "COMPUTER");
		Assertions.assertEquals(category.getTitle(), "삼성컴퓨터");
	}
	
	//@Test
	public void read() {
		//Optional<Category> category =  repo.findById(1L);
		Optional<Category> category = repo.findByType(CategoryType.COMPUTER);
		category.ifPresent(c -> {
			Assertions.assertEquals(c.getType(), "COMPUTER");
			System.out.println(c.toString());
		});
	}
}
