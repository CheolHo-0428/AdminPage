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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy =GenerationType.SEQUENCE, generator = "Category_SEQUENCE_GENERATOR")
	@SequenceGenerator(name = "Category_SEQUENCE_GENERATOR", sequenceName = "Category_SEQUENCE", initialValue = 1, allocationSize = 1)
	private Long id;
	
	private String type;
	
	private String title;
	
	private LocalDateTime createdAt;
	
	private String createdBy;
	
	private LocalDateTime updatedAt;
	
	private String updatedBy;
		
}
