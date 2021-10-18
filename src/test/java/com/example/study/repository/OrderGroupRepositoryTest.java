package com.example.study.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderGroup;

public class OrderGroupRepositoryTest extends StudyApplicationTests{
	
	@Autowired
	private OrderGroupRepository repo;
	
	@Test
	public void create() {
		OrderGroup orderGroup = new OrderGroup();
		
		orderGroup.setStatus("COMPLETE");
		orderGroup.setOrderType("ALL");
		orderGroup.setRevAddress("서울시 강남구");
		orderGroup.setRevName("홍길순");
		orderGroup.setPaymentType("CARD");
		orderGroup.setTotalPrice(BigDecimal.valueOf(9000000));
		orderGroup.setTotalQuantity(1);
		orderGroup.setOrderAt(LocalDateTime.now().minusDays(2));
		orderGroup.setArrivalDate(LocalDateTime.now());
		orderGroup.setCreatedAt(LocalDateTime.now());
		orderGroup.setCreatedBy("AdminServer");
//		orderGroup.setUsersId(1L);
		
		OrderGroup newOg = repo.save(orderGroup);
		Assertions.assertNotNull(newOg);
	}
}
