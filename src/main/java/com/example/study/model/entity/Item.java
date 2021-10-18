package com.example.study.model.entity;

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
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	
	@Id
	@GeneratedValue(strategy =GenerationType.SEQUENCE, generator = "Item_SEQUENCE_GENERATOR")
	@SequenceGenerator(name = "Item_SEQUENCE_GENERATOR", sequenceName = "Item_SEQUENCE", initialValue = 1, allocationSize = 1)
	private Long id;
	
	private String status;
	
	private String name;
	
	private String title;

	private String content;
	
	private Integer price;
	
	private String brandName;
	
	private LocalDateTime registeredAt;
	
	private LocalDateTime unregisteredAt;
	
	private LocalDateTime createdAt;
	
	private String createdBy;
	
	private LocalDateTime updatedAt;
	
	private String updatedBy;
	
//	private Long partnerId;
	
	
	
}
