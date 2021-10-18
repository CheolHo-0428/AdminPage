package com.example.study.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OrderGroup {
	
	@Id
	@GeneratedValue(strategy =GenerationType.SEQUENCE, generator = "OrderGroup_SEQUENCE_GENERATOR")
	@SequenceGenerator(name = "OrderGroup_SEQUENCE_GENERATOR", sequenceName = "OrderGroup_SEQUENCE", initialValue = 1, allocationSize = 1)
	private Long id;
	
	private String status;
	
	private String orderType;
	
	private String revAddress;
	
	private String revName;
	
	private String paymentType;
	
	private BigDecimal totalPrice;
	
	private Integer totalQuantity;
	
	private LocalDateTime orderAt;
	
	private LocalDateTime arrivalDate;
	
	private LocalDateTime createdAt;
	
	private String createdBy;
	
	private LocalDateTime updatedAt;
	
	private String updatedBy;
	
//	private Long userId;
	
}
