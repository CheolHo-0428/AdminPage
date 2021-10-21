package com.example.study.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AdminUsersRole {
	
	MANAGER(0, "매니저", "관리자 등급 매니저"),
	SUPER(1, "슈퍼매니저", "관리자 등급 슈퍼매니저");
	
	private Integer id;
	
	private String title;
	
	private String description;
	
}
