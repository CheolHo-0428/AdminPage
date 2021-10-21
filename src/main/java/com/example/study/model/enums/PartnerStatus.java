package com.example.study.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PartnerStatus {
	REGISTERED(0, "등록", "파트너 등록"),
	UNREGISTERED(1, "등록해지", "파트너 등록 해지");
	
	private Integer id;
	
	private String title;
	
	private String description;
}
