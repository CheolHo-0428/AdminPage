package com.example.study.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryType {

	COMPUTER(0,"COMPUTER","데스크탑 컴퓨터"),
	CLOTHING(1, "CLOTHING", "의류"),
	MULTI_SHOP(2, "MULTI_SHOP", "멀티샵"),
	INTERIOR(3, "INTERIOR", "인테리어"),
	FOOD(4, "FOOD", "식품"),
	SPORTS(5, "SPORTS", "스포츠용품"),
	SHOPPING_MALL(6, "SHOPPING_MALL", "쇼핑몰"),
	DUTY_FREE(7, "DUTY_FREE", "면세품"),
	BEAUTY(8, "BEAUTY", "미용품");
	
	private Integer id;
	
	private String title;
	
	private String description;
}
