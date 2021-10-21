package com.example.study.model.network.response;

import java.time.LocalDateTime;

import com.example.study.model.enums.AdminUsersRole;
import com.example.study.model.enums.AdminUsersStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminUsersApiResponse {
	
	private Long id;

	private String account;
	
	private String password;
	
	private AdminUsersStatus status;
	
	private AdminUsersRole role;
	
	private LocalDateTime lastLoginAt;
	
	private LocalDateTime passwordUpdatedAt;
	
	private int loginFailCount;
	
	private LocalDateTime registeredAt;
	
	private LocalDateTime unregisteredAt;
}
