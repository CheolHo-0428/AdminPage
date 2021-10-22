package com.example.study.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderDetailStatus {
	
	ORDERING(0,"주문중", "주문중"),
	COMPLETE(1, "배송완료", "배송완료"),
	CONFIRM(2, "확인", "확인");
	
	private Integer id;
	
	private String title;
	
	private String description;
}
