package com.example.study.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;
import com.example.study.model.entity.Partner;

public class PartnerRepositoryTest extends StudyApplicationTests {
	
	@Autowired
	private PartnerRepository repo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Test
	public void create() {
		
		Partner partner = new Partner();
		
		partner.setName("Partner01");
		partner.setStatus("REGISTERED");
		partner.setAddress("서울시 강남구");
		partner.setCallCenter("070-1111-9988");
		partner.setPartnerNumber("070-333-9988");
		partner.setBusinessNumber("070-333-9988");
		partner.setCeoName("홍길동");
		partner.setRegisteredAt(LocalDateTime.now());
		partner.setCreatedAt(LocalDateTime.now());
		partner.setCreatedBy("AdminServer");
		
		Optional<Category> category = categoryRepo.findById(1L);
		category.ifPresent(c -> {
			partner.setCategory(c);
		});
		
		Partner newPartner = repo.save(partner);
		Assertions.assertNotNull(newPartner);
	}
	
	//@Test
	public void read() {
		
	}
}
