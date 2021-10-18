package com.example.study.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;

public class ItemRepositoryTest extends StudyApplicationTests{
	
	@Autowired
	ItemRepositroy repo;
	
	@Test
	public void create() {
		Item item = new Item();
		item.setStatus("UNREGISTERED");
		item.setName("삼성 아트북");
		item.setTitle("삼성 아트북 A-100");
		item.setContent("2021년형 신형 노트북");
		item.setPrice(1000000);
		item.setBrandName("삼성");
		item.setRegisteredAt(LocalDateTime.now());
		item.setCreatedAt(LocalDateTime.now());
		item.setCreatedBy("AdminServer");
//		item.setPartnerId(1L);
		
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
