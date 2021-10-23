package com.example.study.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.study.model.entity.AdminUsers;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.AdminUsersApiRequest;
import com.example.study.model.network.response.AdminUsersApiResponse;

@Service
public class AdminUsersApiLogicService extends BaseService<AdminUsersApiRequest, AdminUsersApiResponse, AdminUsers> {
	
	// JpaRepository<AdminUsers, Long> baseRepo === AdminUsersRepository AdminUsersRepository
	
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
		
		AdminUsers newAdminUsers =  baseRepo.save(adminUsers);
		
		return Header.OK(response(newAdminUsers));
	}

	@Override
	public Header<AdminUsersApiResponse> read(Long id) {
		// 1.데이터를 찾는다.
		Optional<AdminUsers> optional =  baseRepo.findById(id);
		
		return optional
				.map(adminUsers -> Header.OK(response(adminUsers)))
				.orElseGet(() -> Header.ERROR("데이터가 없습니다."));
	}
	
	
	@Override
	public Header<List<AdminUsersApiResponse>> search(Pageable pageable) {
		Page<AdminUsers> adminUsers = baseRepo.findAll(pageable);
		
		List<AdminUsersApiResponse> adminList = adminUsers.stream()
					.map(adminUser -> response(adminUser))
					.collect(Collectors.toList());
		
		Pagination pagination = Pagination.builder()
				.totalPages(adminUsers.getTotalPages())
				.totalElements(adminUsers.getTotalElements())
				.currentPage(adminUsers.getNumber())
				.currentElements(adminUsers.getNumberOfElements())
				.build();
		
		return Header.OK(adminList, pagination);
	}

	@Override
	public Header<AdminUsersApiResponse> update(Header<AdminUsersApiRequest> request) {
		//1. 요청받는 데이터를 받는다.
		AdminUsersApiRequest apiRequest = request.getData();
		
		//2.업데이트할 데이터를 찾는다.
		Optional<AdminUsers> optional = baseRepo.findById(apiRequest.getId());
		
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
				.map(updateAdminUsers -> baseRepo.save(updateAdminUsers))
				.map(updateAdminUsers -> Header.OK(response(updateAdminUsers)))
				.orElseGet(() -> Header.ERROR("업데이트할 데이터가 없습니다."));
	}

	@Override
	public Header delete(Long id) {
		// 1.데이터를 찾는다.
		Optional<AdminUsers> optional =  baseRepo.findById(id);
		return optional
				.map(adminUsers -> {
					baseRepo.delete(adminUsers);
					return Header.OK();
				})
				.orElseGet(() -> Header.ERROR("삭제할 데이터가 없습니다."));
	}

	
	private AdminUsersApiResponse response(AdminUsers adminUser){
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
		
		return body;
	}
	
}
