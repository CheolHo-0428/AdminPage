package com.example.study.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Pagination {
	
	private Integer totalPages; // 총 몇개의 페이지인지?
	
	private Long totalElements; // 총 몇개의 엘리먼트를 가지고 있는지?
	
	private Integer currentPage; // 현재 페이지는 몇번째인지?
	
	private Integer currentElements; // 현재 몇개의 데이터를 가지고 있는지?
	
}
