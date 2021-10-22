package com.example.study.sample;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;
import com.example.study.model.enums.CategoryType;
import com.example.study.repository.CategoryRepository;

public class CategorySample extends StudyApplicationTests {
	
	@Autowired
	private CategoryRepository repo;
	
	@Test
	public void createSample() {
		List<CategoryType> categoryList = Arrays.asList(
				CategoryType.COMPUTER,
				CategoryType.CLOTHING,
				CategoryType.MULTI_SHOP,
				CategoryType.INTERIOR,
				CategoryType.FOOD,
				CategoryType.SPORTS,
				CategoryType.SHOPPING_MALL,
				CategoryType.DUTY_FREE,
				CategoryType.BEAUTY
				);
		List<String> titleList = Arrays.asList("컴퓨터-전자제품","의류","멀티샵","인테리어","음식","스포츠","쇼핑몰","면세점","화장");
		
		for (int i = 0; i < categoryList.size(); i++) {
			
			Category create = Category.builder()
					.type(categoryList.get(i))
					.title(titleList.get(i))
					.build();
			repo.save(create);
		}
	}
}
