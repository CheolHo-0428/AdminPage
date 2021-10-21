package com.example.study.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderGroupOrderType {
	//enum(id, title, description)
	ALL(0, "일괄배송", "모든 상품을 묶음 배송"),
	EACH(1, "개별배송", "상품이 준비되는대로 배송");
	
	private Integer id;
	
	private String title;
	
	private String description;
}
