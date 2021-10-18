package com.example.study.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.entity.Users;

public class OrderGroupRepositoryTest extends StudyApplicationTests{
	
	@Autowired
	private OrderGroupRepository repo;
	
	@Autowired
	private UsersRepository userRepo;
	
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
		
		Optional<Users> users = userRepo.findById(1L);
		users.ifPresent(user -> {
			orderGroup.setUsers(user);
		});
		
		OrderGroup newOg = repo.save(orderGroup);
		Assertions.assertNotNull(newOg);
	}
	
	//@Test
	public void read() {
		
	}
}
