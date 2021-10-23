package com.example.study.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.study.model.entity.Users;
import com.example.study.model.enums.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;

@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, Users>{
	
	// JpaRepository<Users, Long> baseRepo === UsersRepository usersRepository
	
	// 1. request data
	// 2. user 생성
	// 3. 생성된 데이터 -> UserApiResponse return
	@Override
	public Header<UserApiResponse> create(Header<UserApiRequest> request) {
		
		// 1. request data
		UserApiRequest userApiRequest = request.getData();
		
		// 2. user 생성
		Users user = Users.builder()
				.account(userApiRequest.getAccount())
				.password(userApiRequest.getPassword())
				.status(UserStatus.REGISTERED)
				.phoneNumber(userApiRequest.getPhoneNumber())
				.email(userApiRequest.getEmail())
				.registeredAt(LocalDateTime.now())
				.build();
					
		Users newUser =  baseRepo.save(user);
		
		// 3. 생성된 데이터 -> UserApiResponse return
		return Header.OK(response(newUser));
	}

	@Override
	public Header<UserApiResponse> read(Long id) {
		// id -> repository getOne, getById
		Optional<Users> optional = baseRepo.findById(id);
		
		// user -> userApiResponse return
		return optional
				.map(user -> Header.OK(response(user)))
				.orElseGet(() -> Header.ERROR("데이터 없음"));	
	}

	@Override
	public Header<UserApiResponse> update(Header<UserApiRequest> request) {
		
		// 1. data
		UserApiRequest userApiRequest = request.getData();
		// 2. id -> user 데이터를 찾고
		Optional<Users> optional = baseRepo.findById(userApiRequest.getId());
		
		// 3. update
		return optional.map(user -> {
				user.setAccount(userApiRequest.getAccount())
					.setPassword(userApiRequest.getPassword())
					.setStatus(userApiRequest.getStatus())
					.setPhoneNumber(userApiRequest.getPhoneNumber())
					.setEmail(userApiRequest.getEmail())
					.setRegisteredAt(userApiRequest.getRegisteredAt())
					.setUnregisteredAt(userApiRequest.getUnregisteredAt());
				return user;
			})
				.map(user -> baseRepo.save(user))
				.map(updateUser -> Header.OK(response(updateUser)))
				.orElseGet(() -> Header.ERROR("데이터 없음")); // 4. userApiResponse	
	}

	@Override
	public Header delete(Long id) {
		// 1.id -> repository -> user
		Optional<Users> optional = baseRepo.findById(id);
		
		// 2. repository -> delete
		return optional.map(user -> {
					baseRepo.delete(user);
					return Header.OK();
					})
					.orElseGet(() -> Header.ERROR("데이터 없음"));
	}
	
	
	public UserApiResponse response(Users users){
		// user -> userApiResponse
		
		UserApiResponse userApiResponse = UserApiResponse.builder()
											.id(users.getId())
											.account(users.getAccount())
											.password(users.getPassword())
											.status(users.getStatus())
											.email(users.getEmail())
											.phoneNumber(users.getPhoneNumber())
											.registeredAt(users.getRegisteredAt())
											.unregisteredAt(users.getUnregisteredAt())
											.build();
		
		return userApiResponse;
	}
	
	public Header<List<UserApiResponse>> search(Pageable pageable){
		
		Page<Users> page = baseRepo.findAll(pageable);
		
		List<UserApiResponse> userApiResponseList = page.stream()
				.map(user -> response(user))
				.collect(Collectors.toList());
		
		Pagination pagination = Pagination.builder()
				.totalPages(page.getTotalPages())
				.totalElements(page.getTotalElements())
				.currentPage(page.getNumber())
				.currentElements(page.getNumberOfElements())
				.build();
		
		return Header.OK(userApiResponseList, pagination);
	}
	
	
}
