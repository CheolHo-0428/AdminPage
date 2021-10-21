package com.example.study.model.network.response;

import java.time.LocalDateTime;

import com.example.study.model.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiResponse {
	
	private Long id;
	
	private String account;
	
	private String password;
	
	private UserStatus status;
	
	private String email;
	
	private String phoneNumber;
	
	private LocalDateTime registeredAt;
	
	private LocalDateTime unregisteredAt;
}
