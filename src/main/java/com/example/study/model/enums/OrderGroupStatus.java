package com.example.study.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderGroupStatus {
	
	READY(0,"준비중", "배송 준비중"),
	SHIPPING(1, "배송중", "상품 배송중"),
	COMPLETE(2, "배송완료", "상품 배송완료");
	
	private Integer id;
	
	private String title;
	
	private String description;
	
}
