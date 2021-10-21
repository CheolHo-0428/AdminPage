package com.example.study.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderGroupPaymentType {
	//enum(id, title, description)
	CARD(0, "신용카드", "신용카드 결제"),
	DEPOSIT(1, "입금", "계좌로 입금");
	
	private Integer id;
	
	private String title;
	
	private String description;
	
}
