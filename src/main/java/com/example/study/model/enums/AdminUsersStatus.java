package com.example.study.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AdminUsersStatus {

	REGISTERED(0, "등록", "관리자 등록"),
	UNREGISTERED(1, "해지", "관리자 해지");
	
	private Integer id;
	
	private String title;
	
	private String description;
}
