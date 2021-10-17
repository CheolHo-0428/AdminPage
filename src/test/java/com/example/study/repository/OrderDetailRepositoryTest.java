package com.example.study.repository;

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
		od.setUserr(null);
		od.setOrderAt(LocalDateTime.now());
		
		OrderDetail newod =  repo.save(od);
		
		Assertions.assertNotNull(newod);
		
	}
}
