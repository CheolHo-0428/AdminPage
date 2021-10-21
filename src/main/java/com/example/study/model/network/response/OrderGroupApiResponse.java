package com.example.study.model.network.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.study.model.enums.OrderGroupOrderType;
import com.example.study.model.enums.OrderGroupPaymentType;
import com.example.study.model.enums.OrderGroupStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderGroupApiResponse {
	
	private Long id;
	
	private OrderGroupStatus status;
	
	private OrderGroupOrderType orderType;
	
	private String revAddress;
	
	private String revName;
	
	private OrderGroupPaymentType paymentType;
	
	private BigDecimal totalPrice;
	
	private Integer totalQuantity;
	
	private LocalDateTime orderAt;
	
	private LocalDateTime arrivalDate;
	
	private Long UsersId;
}
