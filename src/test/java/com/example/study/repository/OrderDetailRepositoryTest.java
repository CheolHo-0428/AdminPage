package com.example.study.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.OrderDetail;
import com.example.study.model.entity.OrderGroup;

public class OrderDetailRepositoryTest extends StudyApplicationTests {
	
	@Autowired
	private OrderDetailRepository repo;
	
	@Autowired
	private OrderGroupRepository orderGroupRepo;
	
	@Autowired
	private ItemRepositroy itemRepo;
	
	@Test
	public void create() {
		
		OrderDetail od = new OrderDetail();
		od.setStatus("WAITING");
		od.setArrivalDate(LocalDateTime.now().plusDays(2));
		od.setQuantity(1);
		od.setTotalPrice(BigDecimal.valueOf(100000));
		od.setCreatedAt(LocalDateTime.now());
		od.setCreatedBy("AdminServer");
		
		Optional<OrderGroup> orderGroup = orderGroupRepo.findById(1L);
		orderGroup.ifPresent(o -> {
			od.setOrderGroup(o);
		});
		
		Optional<Item> item = itemRepo.findById(1L);
		item.ifPresent(i -> {
			od.setItem(i);
		});
		
		OrderDetail newod =  repo.save(od);
		
		Assertions.assertNotNull(newod);
		
	}
}
