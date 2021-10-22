package com.example.study.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Category;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.CategoryApiRequest;
import com.example.study.model.network.response.CategoryApiResponse;
import com.example.study.repository.CategoryRepository;

@Service
public class CategoryApiLogicService extends BaseService<CategoryApiRequest, CategoryApiResponse, Category> {
	
	//JpaRepository<Category, Long> baseRepo === CategoryRepository categoryRepository

	@Override
	public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {
		//1.요청한 데이터를 가져온다.
		CategoryApiRequest apiRequest =  request.getData();
		
		Category category = Category.builder()
					.type(apiRequest.getType())
					.title(apiRequest.getTitle())
					.build();
		
		Category newCategory = baseRepo.save(category);
		
		return response(newCategory);
	}

	@Override
	public Header<CategoryApiResponse> read(Long id) {
		// 1.데이터를 찾는다.
		Optional<Category> optional = baseRepo.findById(id);
		
		return optional
				.map(category -> response(category))
				.orElseGet(() -> Header.ERROR("데이터가 없습니다."));
	}

	@Override
	public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {
		// 1.요청한 데이터를 받아온다.
		CategoryApiRequest apiRequest = request.getData();
		
		// 2.업데이트할 데이터를 찾는다.
		Optional<Category> optional = baseRepo.findById(apiRequest.getId());
		
		// 3.찾은데이터를 엔티티에 넣고, 디비에 저장한다.
		return optional
				.map(category -> {
					category
						.setId(apiRequest.getId())
						.setType(apiRequest.getType())
						.setTitle(apiRequest.getTitle());
					
					return category;
				})
				.map(updateCategory -> baseRepo.save(updateCategory))
				.map(updateCategory -> response(updateCategory))
				.orElseGet(() -> Header.ERROR("업데이트할 데이터가 없습니다."));
	}

	@Override
	public Header delete(Long id) {
		// 1.삭제할 데이터를 찾는다.
		Optional<Category> optional =  baseRepo.findById(id);
		
		return optional
				.map(category -> {
					baseRepo.delete(category);
					return Header.OK();
					})
				.orElseGet(() -> Header.ERROR("삭제할 데이터가 없습니다."));
	}
	
	private Header<CategoryApiResponse> response(Category category){
		
		CategoryApiResponse body = CategoryApiResponse.builder()
					.id(category.getId())
					.type(category.getType())
					.title(category.getTitle())
					.build();
		
		return Header.OK(body);
	}
}
