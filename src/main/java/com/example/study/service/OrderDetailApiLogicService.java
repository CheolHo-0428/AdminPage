package com.example.study.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.study.model.entity.OrderDetail;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.OrderDetailApiRequest;
import com.example.study.model.network.response.OrderDetailApiResponse;
import com.example.study.repository.ItemRepositroy;
import com.example.study.repository.OrderGroupRepository;

@Service
public class OrderDetailApiLogicService extends BaseService<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {
	
	// JpaRepository<OrderDetail, Long> baseRepo === OrderDetailRepository OrderDetailRepository
	
	@Autowired
	private OrderGroupRepository orderGroupRepo;
	
	@Autowired
	private ItemRepositroy itemRepo;
	
	@Override
	public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {
		// 1. 요청데이터를 담는다.
		OrderDetailApiRequest apiRequest = request.getData();
		
		// 2. 받은 데이터를 entity에 넣는다.
		OrderDetail orderDetail = OrderDetail.builder()
						.status(apiRequest.getStatus())
						.arrivalDate(apiRequest.getArrivalDate())
						.quantity(apiRequest.getQuantity())
						.totalPrice(apiRequest.getTotalPrice())
						.orderGroup(orderGroupRepo.getOne(apiRequest.getOrderGroupId()))
						.item(itemRepo.getOne(apiRequest.getItemId()))
						.build();
		
		OrderDetail newOrderDetail = baseRepo.save(orderDetail);
		
		// 3.응답한다.
		OrderDetailApiResponse apiResponse = response(newOrderDetail);
		
		return Header.OK(apiResponse);
	}

	@Override
	public Header<OrderDetailApiResponse> read(Long id) {
		//1. 데이터를 찾는다.
		Optional<OrderDetail> optional = baseRepo.findById(id);
		
		//2. 응답한다.
		return optional
				.map(orderDetail -> response(orderDetail))
				.map(orderDetail -> Header.OK(orderDetail))
				.orElseGet(() -> Header.ERROR("데이터가 없습니다."));
	}
	
	@Override
	public Header<List<OrderDetailApiResponse>> search(Pageable pageable) {
		Page<OrderDetail> page = baseRepo.findAll(pageable);
		
		List<OrderDetailApiResponse> orderDetailList = page.stream()
				.map(orderDetail -> response(orderDetail))
				.collect(Collectors.toList());
		
		Pagination pagination = Pagination.builder()
				.totalPages(page.getTotalPages())
				.totalElements(page.getTotalElements())
				.currentPage(page.getNumber())
				.currentElements(page.getNumberOfElements())
				.build();
		
		return Header.OK(orderDetailList, pagination);
	}

	@Override
	public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
		// 1. 요청데이터를 담는다.
		OrderDetailApiRequest apiRequest =  request.getData();
		
		// 2.업데이트할 데이터를 찾는다.
		Optional<OrderDetail> optional = baseRepo.findById(apiRequest.getId());
		
		// 3. 데이터를 업데이트 시킨다.
		return optional
				.map(orderDetail -> {
					orderDetail
						.setStatus(apiRequest.getStatus())
						.setArrivalDate(apiRequest.getArrivalDate())
						.setQuantity(apiRequest.getQuantity())
						.setTotalPrice(apiRequest.getTotalPrice())
						.setOrderGroup(orderGroupRepo.getOne(apiRequest.getOrderGroupId()))
						.setItem(itemRepo.getOne(apiRequest.getItemId()));
					return orderDetail;
				})
				.map(updateData -> baseRepo.save(updateData))
				.map(updateData -> response(updateData))
				.map(updateData -> Header.OK(updateData))
				.orElseGet(() -> Header.ERROR("업데이트할 데이터가 없습니다."));
	}

	@Override
	public Header delete(Long id) {
		//1. 삭제할 데이터를 찾는다.
		Optional<OrderDetail> optional =  baseRepo.findById(id);
		
		//2. 데이터를 삭제한다.
		return optional
				.map(orderDetail -> {
					baseRepo.delete(orderDetail);
					return Header.OK();
				})
				.orElseGet(() -> Header.ERROR("삭제할 데이터가 없습니다."));
	}
	
	private OrderDetailApiResponse response(OrderDetail detail) {
		
		OrderDetailApiResponse body = OrderDetailApiResponse.builder()
					.id(detail.getId())
					.status(detail.getStatus())
					.arrivalDate(detail.getArrivalDate())
					.quantity(detail.getQuantity())
					.totalPrice(detail.getTotalPrice())
					.orderGroupId(detail.getOrderGroup().getId())
					.itemId(detail.getItem().getId())
					.build();
				
		return body;
	}
	
}
