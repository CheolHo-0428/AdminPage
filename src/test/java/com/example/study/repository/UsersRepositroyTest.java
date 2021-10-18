package com.example.study.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Users;


public class UsersRepositroyTest extends StudyApplicationTests {
	
	@Autowired
	private UserRepository userRepositoy;
	
	@Test
	public void create() {
		Users users = new Users();
		users.setAccount("Test01");
		users.setPassword("12345");
		users.setStatus("REGISTERED");
		users.setEmail("Test01@gmail.com");
		users.setPhoneNumber("010-111-1111");
		users.setRegisteredAt(LocalDateTime.now());
		users.setCreatedAt(LocalDateTime.now());
		users.setCreatedBy("AdminServer");
		
		Users newUsers = userRepositoy.save(users);
		
		Assertions.assertNotNull(newUsers);
		
	}
	
	//@Test
	public void read() {
		Users users = userRepositoy.findByPhoneNumberOrderByIdDesc("010-111-2222");
		Assertions.assertNotNull(users);
		System.out.println(users.toString());
	}
	
	//@Test
	//@Transactional //업데이트 한후에 롤백해준다.
	public void update() {
		Optional<Users> user = userRepositoy.findById(3L);
		
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
		Optional<Users> user = userRepositoy.findById(3L);
		
		Assertions.assertTrue(user.isPresent()); // 값이 존재유무 체크(반드시 값이 들어있어야지 다음 코드를 실행)
		user.ifPresent(selectUser -> {
			userRepositoy.delete(selectUser);
		});
		
		Optional<Users> deleteUser = userRepositoy.findById(3L);
		
//		if(deleteUser.isPresent()) {
//			System.out.println("데이터 존재");
//		} else {
//			System.out.println("데이터 없음");
//		}
		
		Assertions.assertFalse(deleteUser.isPresent()); // 값이 존재유무 체크(반드시 값이 없어야지 다음 코드를 실행)
	}
	
	
}
