package com.example.study.model.network.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.study.model.entity.Item;
import com.example.study.model.entity.OrderGroup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailApiRequest {
	
	private Long id;
	
	private String status;
	
	private LocalDateTime arrivalDate;
	
	private Integer quantity;
	
	private BigDecimal totalPrice;
	
	private Long orderGroupId;
	
	private Long itemId;
}