package com.example.study.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.OrderGroupApiRequest;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.repository.OrderGroupRepository;
import com.example.study.repository.UsersRepository;

@Service
public class OrderGroupApiLogicService extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {
	
	// 위는 JpaRepository<OrderGroup, Long> baseRepo === OrderGroupRepository orderGroupRepository
	
	@Autowired
	private UsersRepository usersRepo;
	
	@Override
	public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
		//1. 데이터를 요청
		OrderGroupApiRequest body = request.getData();
		
		//2. 요청한 데이터 -> Entity에 넣는다.
		OrderGroup orderGroup =  OrderGroup.builder()
				.status(body.getStatus())
				.orderType(body.getOrderType())
				.revAddress(body.getRevAddress())
				.revName(body.getRevName())
				.paymentType(body.getPaymentType())
				.totalPrice(body.getTotalPrice())
				.totalQuantity(body.getTotalQuantity())
				.orderAt(LocalDateTime.now())
				.arrivalDate(body.getArrivalDate())
				.users(usersRepo.getOne(body.getUsersId()))
				.build();
		
		OrderGroup newOrderGroup =  baseRepo.save(orderGroup);
		
		//3. entity에 저장값을 응답한다.
		OrderGroupApiResponse apiResponse = response(newOrderGroup);
		return Header.OK(apiResponse);
	}

	@Override
	public Header<OrderGroupApiResponse> read(Long id) {
		
		Optional<OrderGroup> optional =  baseRepo.findById(id);
		
		return optional
				.map(orderGroup -> response(orderGroup))
//				.map(this :: response)
				.map(orderGroup -> Header.OK(orderGroup))
				.orElseGet(() -> Header.ERROR("데이터가 없습니다."));
	}
	
	
	@Override
	public Header<List<OrderGroupApiResponse>> search(Pageable pageable) {
		Page<OrderGroup> page = baseRepo.findAll(pageable);
		
		List<OrderGroupApiResponse> orderGroupList = page.stream()
					.map(orderGroup -> response(orderGroup))
					.collect(Collectors.toList());
		
		Pagination pagination = Pagination.builder()
				.totalPages(page.getTotalPages())
				.totalElements(page.getTotalElements())
				.currentPage(page.getNumber())
				.currentElements(page.getNumberOfElements())
				.build();
		
		return Header.OK(orderGroupList, pagination);
	}

	@Override
	public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
		//1.업데이트 내용을 요청한다.
		OrderGroupApiRequest body = request.getData();
		
		//2.업데이트 할 데이터를 찾는다.
		Optional<OrderGroup> optional = baseRepo.findById(body.getId());
		
		return optional
			.map(orderGroup -> {
				orderGroup
					.setStatus(body.getStatus())
					.setOrderType(body.getOrderType())
					.setRevAddress(body.getRevAddress())
					.setRevName(body.getRevName())
					.setPaymentType(body.getPaymentType())
					.setTotalPrice(body.getTotalPrice())
					.setTotalQuantity(body.getTotalQuantity())
					.setOrderAt(body.getOrderAt())
					.setArrivalDate(body.getArrivalDate())
					.setUsers(usersRepo.getOne(body.getUsersId()));
				
				return orderGroup;
			})
			.map(updateOrderGroup -> baseRepo.save(updateOrderGroup))
			.map(changeOrderGroup -> response(changeOrderGroup))
			.map(updateOrderGroup -> Header.OK(updateOrderGroup))
			.orElseGet(() -> Header.ERROR("업데이트할 데이터가 없습니다.")); 
	}

	@Override
	public Header delete(Long id) {
		
		Optional<OrderGroup> optional = baseRepo.findById(id);
		
		return optional
				.map(orderGroup -> {
						baseRepo.delete(orderGroup);
						return Header.OK();
					})
				.orElseGet(() -> Header.ERROR("삭제할 데이터가 없습니다."));
	}
	
	private OrderGroupApiResponse response(OrderGroup orderGroup){
		
		OrderGroupApiResponse body = OrderGroupApiResponse.builder()
				.id(orderGroup.getId())
				.status(orderGroup.getStatus())
				.orderType(orderGroup.getOrderType())
				.revAddress(orderGroup.getRevAddress())
				.revName(orderGroup.getRevName())
				.paymentType(orderGroup.getPaymentType())
				.totalPrice(orderGroup.getTotalPrice())
				.totalQuantity(orderGroup.getTotalQuantity())
				.orderAt(orderGroup.getOrderAt())
				.arrivalDate(orderGroup.getArrivalDate())
				.UsersId(orderGroup.getUsers().getId())
				.build();
		
		return body;
	}

	
}
