package com.example.study.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;

public class OrderDetailRepositoryTest extends StudyApplicationTests {
	
	@Autowired
	private OrderDetailRepository repo;
	
	@Test
	public void create() {
		
		OrderDetail od = new OrderDetail();
		od.setStatus("WAITING");
		od.setArrivalDate(LocalDateTime.now().plusDays(2));
		od.setQuantity(1);
		od.setTotalPrice(BigDecimal.valueOf(100000));
		od.setCreatedAt(LocalDateTime.now());
		od.setCreatedBy("AdminServer");
//		od.setOrderGroupId(1L);
//		od.setItemId(1L);
		
		OrderDetail newod =  repo.save(od);
		
		Assertions.assertNotNull(newod);
		
	}
}
