package com.example.study.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.AdminUsers;
import com.example.study.model.enums.AdminUsersRole;
import com.example.study.model.enums.AdminUsersStatus;

public class AdminUsersRepositoryTest extends StudyApplicationTests{
	@Autowired
	private AdminUsersRepository repo;
	
	@Test
	public void create() {
		AdminUsers adminUsers = new AdminUsers();
		
		adminUsers.setAccount("AdminUser01");
		adminUsers.setPassword("12345");
		adminUsers.setStatus(AdminUsersStatus.REGISTERED);
		adminUsers.setRole(AdminUsersRole.MANAGER);
//		adminUsers.setCreatedAt(LocalDateTime.now());
//		adminUsers.setCreatedBy("AdminServer");
		
		AdminUsers newAd = repo.save(adminUsers);
		
		Assertions.assertNotNull(newAd);
		
		newAd.setAccount("AdminUser03");
		repo.save(newAd);
	}
}
