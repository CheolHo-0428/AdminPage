package com.example.study.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderDetailStatus {
	REGISTERED(0, "등록", "주문상품 등록"),
	UNREGISTERED(1, "등록해지", "주문상품 해지");
	
	private Integer id;
	
	private String title;
	
	private String description;
}
