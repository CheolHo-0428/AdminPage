package com.example.study.repository;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;

public class ItemRepositoryTest extends StudyApplicationTests{
	
	@Autowired
	ItemRepositroy repo;
	
	@Test
	public void create() {
		Item item = new Item();
		
		item.setName("노트북");
		item.setPrice(1000000);
		item.setContent("삼성 노트북");
		
		Item newItem = repo.save(item);
		Assertions.assertNotNull(newItem);
	}
	
	
	//@Test
	public void read() {
		Optional<Item>item = repo.findById(2L);
		Assertions.assertTrue(item.isPresent());
		item.ifPresent(selectItem -> {
			System.out.println("item[1]: " + selectItem);
		});
	}
	
}
