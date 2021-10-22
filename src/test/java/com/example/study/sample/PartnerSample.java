package com.example.study.sample;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;
import com.example.study.model.entity.Partner;
import com.example.study.model.enums.PartnerStatus;
import com.example.study.repository.CategoryRepository;
import com.example.study.repository.PartnerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PartnerSample extends StudyApplicationTests{
	
	@Autowired
	private PartnerRepository prepo;
	
	@Autowired
	private CategoryRepository crepo;
	
	private Random random;
	
	@Test
	public void createSample() {
	
		random = new Random();
		List<Category> categoryList = crepo.findAll(); 
		
		for (int i = 0; i < categoryList.size(); i++) {
			Category category = categoryList.get(i);
			
			for (int j = 0; j < 10; j++) {
				int div = (random.nextInt(10)+1)%2;
				PartnerStatus status = (div==0 ? PartnerStatus.REGISTERED : PartnerStatus.UNREGISTERED);
				
				Partner partner = Partner.builder()
						.name(category.getTitle() + " " + j + "호점")
						.status(status)
						.address("서울시 강남구 "+j+"번길"+random.nextInt(100)+1+"호")
						.callCenter("070-"+String.format("%04d", random.nextInt(100)+1)+"-"+String.format("%04d", random.nextInt(100)+1))
						.partnerNumber("010-1111-"+String.format("%04d", i))
						.businessNumber((random.nextInt(999999999)+1)+""+j)
						.ceoName(j + " 대표")
						.registeredAt(getRandomDate())
						.unregisteredAt(status.equals(PartnerStatus.UNREGISTERED) ? getRandomDate() : null)
						.category(category)
						.build();
				
				log.info("{}", partner);
				prepo.save(partner);
			}
		}
	}
	
	private LocalDateTime getRandomDate() {
		return LocalDateTime.of(2021, getRandomNumber(),getRandomNumber(),getRandomNumber(),getRandomNumber(),getRandomNumber());
	}
	
	private int getRandomNumber() {
		return random.nextInt(11)+1;
	}
	
}
