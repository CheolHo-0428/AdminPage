package com.example.study.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.Partner;

public class ItemRepositoryTest extends StudyApplicationTests{
	
	@Autowired
	private ItemRepositroy repo;
	
	@Autowired
	private PartnerRepository partnerRepo;
	
	@Test
	public void create() {
		Item item = new Item();
		item.setStatus("UNREGISTERED");
		item.setName("삼성 아트북");
		item.setTitle("삼성 아트북 A-100");
		item.setContent("2021년형 신형 노트북");
		item.setPrice(BigDecimal.valueOf(1000000));
		item.setBrandName("삼성");
		item.setRegisteredAt(LocalDateTime.now());
		item.setCreatedAt(LocalDateTime.now());
		item.setCreatedBy("AdminServer");
		
		Optional<Partner> partner = partnerRepo.findById(1L);
		partner.ifPresent(p -> {
			item.setPartner(p);
		});
		
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
