package com.example.study.service;

import java.security.cert.PKIXRevocationChecker.Option;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.AdminUsers;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.AdminUsersApiRequest;
import com.example.study.model.network.response.AdminUsersApiResponse;
import com.example.study.repository.AdminUsersRepository;

@Service
public class AdminUsersApiLogicService implements CrudInterface<AdminUsersApiRequest, AdminUsersApiResponse> {
	
	@Autowired
	private AdminUsersRepository repo;

	@Override
	public Header<AdminUsersApiResponse> create(Header<AdminUsersApiRequest> request) {
		//1.요청받은 데이터를 받아온다.
		AdminUsersApiRequest apiRequest = request.getData();
		
		//2.요청받은데이터 -> entitiy -> db저장
		AdminUsers adminUsers = AdminUsers.builder()
				.account(apiRequest.getAccount())
				.password(apiRequest.getPassword())
				.status(apiRequest.getStatus())
				.role(apiRequest.getRole())
				.lastLoginAt(apiRequest.getLastLoginAt())
				.registeredAt(LocalDateTime.now())
				.build();
		
		AdminUsers newAdminUsers =  repo.save(adminUsers);
		
		return response(newAdminUsers);
	}

	@Override
	public Header<AdminUsersApiResponse> read(Long id) {
		// 1.데이터를 찾는다.
		Optional<AdminUsers> optional =  repo.findById(id);
		
		return optional
				.map(adminUsers -> response(adminUsers))
				.orElseGet(() -> Header.ERROR("데이터가 없습니다."));
	}

	@Override
	public Header<AdminUsersApiResponse> update(Header<AdminUsersApiRequest> request) {
		//1. 요청받는 데이터를 받는다.
		AdminUsersApiRequest apiRequest = request.getData();
		
		//2.업데이트할 데이터를 찾는다.
		Optional<AdminUsers> optional = repo.findById(apiRequest.getId());
		
		//3.데이터를 업데이트 시킨다.
		return optional
				.map(adminUsers -> {
					adminUsers
						.setId(apiRequest.getId())
						.setAccount(apiRequest.getAccount())
						.setPassword(apiRequest.getPassword())
						.setStatus(apiRequest.getStatus())
						.setRole(apiRequest.getRole())
						.setLastLoginAt(apiRequest.getLastLoginAt())
						.setPasswordUpdatedAt(apiRequest.getPasswordUpdatedAt())
						.setLoginFailCount(apiRequest.getLoginFailCount())
						.setRegisteredAt(apiRequest.getRegisteredAt())
						.setUnregisteredAt(apiRequest.getUnregisteredAt());
					
					return adminUsers;
				})
				.map(updateAdminUsers -> repo.save(updateAdminUsers))
				.map(updateAdminUsers -> response(updateAdminUsers))
				.orElseGet(() -> Header.ERROR("업데이트할 데이터가 없습니다."));
	}

	@Override
	public Header delete(Long id) {
		// 1.데이터를 찾는다.
		Optional<AdminUsers> optional =  repo.findById(id);
		return optional
				.map(adminUsers -> {
					repo.delete(adminUsers);
					return Header.OK();
				})
				.orElseGet(() -> Header.ERROR("삭제할 데이터가 없습니다."));
	}

	
	private Header<AdminUsersApiResponse> response(AdminUsers adminUser){
		AdminUsersApiResponse body = AdminUsersApiResponse.builder()
					.id(adminUser.getId())
					.account(adminUser.getAccount())
					.password(adminUser.getPassword())
					.status(adminUser.getStatus())
					.role(adminUser.getRole())
					.lastLoginAt(adminUser.getLastLoginAt())
					.passwordUpdatedAt(adminUser.getPasswordUpdatedAt())
					.loginFailCount(adminUser.getLoginFailCount())
					.registeredAt(adminUser.getRegisteredAt())
					.unregisteredAt(adminUser.getUnregisteredAt())
					.build();
		
		return Header.OK(body);
	}
	
}
