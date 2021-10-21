package com.example.study.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryType {

	COMPUTER(0, "컴퓨터", "컴퓨터"),
	CELLPHONE(1, "핸드폰", "스마트폰"),
	TV(2, "TV", "TV");
	
	private Integer id;
	
	private String title;
	
	private String description;
}
