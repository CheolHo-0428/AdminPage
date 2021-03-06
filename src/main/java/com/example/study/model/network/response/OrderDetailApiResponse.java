package com.example.study.model.network.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.study.model.enums.OrderDetailStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailApiResponse {
	
	private Long id;
	
	private OrderDetailStatus status;
	
	private LocalDateTime arrivalDate;
	
	private Integer quantity;
	
	private BigDecimal totalPrice;
	
	private Long orderGroupId;
	
	private Long itemId;
}
