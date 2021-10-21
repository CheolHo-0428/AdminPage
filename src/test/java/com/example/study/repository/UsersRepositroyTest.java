package com.example.study.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Users;
import com.example.study.model.enums.UserStatus;


public class UsersRepositroyTest extends StudyApplicationTests {
	
	@Autowired
	private UsersRepository userRepositoy;
	
	@Test
	public void create() {
		String account = "Test04";
		String password = "12345";
		String status = "REGISTERED";
		String email = "Test04@gmail.com";
		String phoneNumber = "010-111-4444";
		LocalDateTime registeredAt = LocalDateTime.now();
		
//		Users users = new Users();
//		users.setAccount(account);
//		users.setPassword(password);
//		users.setStatus(status);
//		users.setEmail(email);
//		users.setPhoneNumber(phoneNumber);
//		users.setRegisteredAt(registeredAt);
//		users.setCreatedAt(LocalDateTime.now());
//		users.setCreatedBy("AdminServer");
		
//		Users newUsers = userRepositoy.save(users);
		
//		Assertions.assertNotNull(newUsers);
		
		//Builder 방식 create를 할경우 자주 사용		
//		Users newUsers1 = Users.builder().account(account).
//				password(password).status(status).email(email).
//				phoneNumber(phoneNumber).registeredAt(registeredAt).build();
//		
//		userRepositoy.save(newUsers1);
		
		//체이닝방식 update를 할경우 자주 사용
		Users newUser2 = new Users().setAccount(account)
								.setPassword(password)
								.setStatus(UserStatus.REGISTERED)
								.setEmail(email)
								.setPhoneNumber(phoneNumber)
								.setRegisteredAt(registeredAt);
		
		userRepositoy.save(newUser2);
		
		
		
	}
	
	//@Test
	//@Transactional
	public void read() {
		Users users = userRepositoy.findByPhoneNumberOrderByIdDesc("010-111-1111");
		Assertions.assertNotNull(users);
		
		users.getOrderGroupList().stream().forEach(orderGroup -> {
			System.out.println("-------------------주문묶음-------------------");
			System.out.println("수령인: " + orderGroup.getRevName());
			System.out.println("수령지: " + orderGroup.getRevAddress());
			System.out.println("총금액: " + orderGroup.getTotalPrice());
			System.out.println("총수량: " + orderGroup.getTotalQuantity());
			
			System.out.println("-------------------주문상세-------------------");
			orderGroup.getOrderDetailList().stream().forEach(orderDetail -> {
				System.out.println("주문상태: " + orderDetail.getStatus());
				System.out.println("도착예정일자: " + orderDetail.getArrivalDate());
				System.out.println("주문상품: " + orderDetail.getItem().getName());
				System.out.println("고객센터: " + orderDetail.getItem().getPartner().getCallCenter());
				System.out.println("파트너사: " + orderDetail.getItem().getPartner().getName());
				System.out.println("카테고리: " + orderDetail.getItem().getPartner().getCategory().getTitle());
			});
		});
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
