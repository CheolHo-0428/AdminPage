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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderDetail {
	
	@Id
	@GeneratedValue(strategy =GenerationType.SEQUENCE, generator = "OrderDetail_SEQUENCE_GENERATOR")
	@SequenceGenerator(name = "OrderDetail_SEQUENCE_GENERATOR", sequenceName = "OrderDetail_SEQUENCE", initialValue = 1, allocationSize = 1)
	private Long id;
	
	private String status;
	
	private LocalDateTime arrivalDate;
	
	private Integer quantity;
	
	private BigDecimal totalPrice;
	
	private LocalDateTime createdAt;
	
	private String createdBy;
	
	private LocalDateTime updatedAt;
	
	private String updatedBy;
	
//	private Long orderGroupId;
	
//	private Long itemId;
	
	
}
