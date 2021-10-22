package com.example.study.sample;

import java.time.LocalDateTime;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Users;
import com.example.study.model.enums.UserStatus;
import com.example.study.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UsersSample extends StudyApplicationTests {
	@Autowired
	private UsersRepository repo;
	
	private Random random;
	
	@Test
	public void sampleCreate() {
		random = new Random();
		for (int i = 0; i < 1001; i++) {
			int div = (random.nextInt(10)+1)%2;
			
			UserStatus status = (div == 0 ? UserStatus.REGISTERED : UserStatus.UNREGISTERED);
			
			Users users = Users.builder()
					.account("TestUser" + i)
					.password("12345")
					.status(status)
					.email("TestUser" + i + "@gmail.com")
					.phoneNumber("010-1111-"+String.format("%04d", i))
					.registeredAt(getRandomDate())
					.unregisteredAt(status.equals(UserStatus.UNREGISTERED) ? getRandomDate() : null )
					.build();
			
			repo.save(users);
			log.info("{}", users);
		}
	}
	
	private LocalDateTime getRandomDate() {
		return LocalDateTime.of(2021, getRandomNumber(),getRandomNumber(),getRandomNumber(),getRandomNumber(),getRandomNumber());
	}
	
	private int getRandomNumber() {
		return random.nextInt(11)+1;
	}
}
