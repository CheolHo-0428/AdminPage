package com.example.study.model.network.response;

import com.example.study.model.enums.CategoryType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryApiResponse {
	
	private Long id;
	
	private CategoryType type;
	
	private String title;
}
