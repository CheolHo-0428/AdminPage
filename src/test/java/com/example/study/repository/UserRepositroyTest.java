package com.example.study.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.Userr;


public class UserRepositroyTest extends StudyApplicationTests {
	
	@Autowired
	private UserRepository userRepositoy;
	
	//@Test
	public void create() {
		Userr user = new Userr();
		user.setAccount("TestUser01");
		user.setEmail("test01@gmail.com");
		user.setPhoneNumber("010-111-1111");
		user.setCreatedAt(LocalDateTime.now());
		user.setCreatedBy("admin");
		
		Userr newUser = userRepositoy.save(user);
		System.out.println(newUser.toString());
	}
	
	@Test
	public void read() {
		Optional<Userr> user = userRepositoy.findByEmail("test01@gmail.com");
		
		user.ifPresent(selectUser -> {
			selectUser.getOrderDetail().stream().forEach(detail -> {
				Item item = detail.getItem();
				System.out.println("Item_id: " + item);
			});
		});
		
		
		
		
		/*
		 * Optional<Userr> user = userRepositoy.findById(2L);
		 * 
		 * user.ifPresent(selectUser -> { System.out.println("user: " +
		 * selectUser.getAccount()); System.out.println("user: " +
		 * selectUser.getEmail()); });
		 */
	}
	
	//@Test
	//@Transactional //업데이트 한후에 롤백해준다.
	public void update() {
		Optional<Userr> user = userRepositoy.findById(3L);
		
		user.ifPresent(selectUser -> {
			selectUser.setAccount("changeTestUser03");
			selectUser.setUpdatedAt(LocalDateTime.now());
			selectUser.setUpdatedBy("update Method()");
			
			userRepositoy.save(selectUser);
		});
	}
	
	//@Test
	//@Transactional //delete 한후에 롤백해준다.
	public void delete() {
		Optional<Userr> user = userRepositoy.findById(3L);
		
		Assertions.assertTrue(user.isPresent()); // 값이 존재유무 체크(반드시 값이 들어있어야지 다음 코드를 실행)
		user.ifPresent(selectUser -> {
			userRepositoy.delete(selectUser);
		});
		
		Optional<Userr> deleteUser = userRepositoy.findById(3L);
		
//		if(deleteUser.isPresent()) {
//			System.out.println("데이터 존재");
//		} else {
//			System.out.println("데이터 없음");
//		}
		
		Assertions.assertFalse(deleteUser.isPresent()); // 값이 존재유무 체크(반드시 값이 없어야지 다음 코드를 실행)
	}
	
	
}
