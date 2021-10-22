package com.example.study.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderGroupPaymentType {
	//enum(id, title, description)
	BANK_TRANSFER(0, "입금", "계좌로 입금"),
	CARD(1, "신용카드", "신용카드 결제"),
	CHECK_CARD(2, "체크카드", "체크카드 결제");
	
	private Integer id;
	
	private String title;
	
	private String description;
	
}
