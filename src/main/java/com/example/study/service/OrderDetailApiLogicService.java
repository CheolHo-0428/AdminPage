package com.example.study.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.OrderDetail;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.OrderDetailApiRequest;
import com.example.study.model.network.response.OrderDetailApiResponse;
import com.example.study.repository.ItemRepositroy;
import com.example.study.repository.OrderDetailRepository;
import com.example.study.repository.OrderGroupRepository;

@Service
public class OrderDetailApiLogicService implements CrudInterface<OrderDetailApiRequest, OrderDetailApiResponse> {
	
	@Autowired
	private OrderDetailRepository orderDetailRepo;
	
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
		
		OrderDetail newOrderDetail = orderDetailRepo.save(orderDetail);
		
		// 3.응답한다.
		return response(newOrderDetail);
	}

	@Override
	public Header<OrderDetailApiResponse> read(Long id) {
		//1. 데이터를 찾는다.
		Optional<OrderDetail> optional = orderDetailRepo.findById(id);
		
		//2. 응답한다.
		return optional
				.map(orderDetail -> response(orderDetail))
				.orElseGet(() -> Header.ERROR("데이터가 없습니다."));
	}

	@Override
	public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
		// 1. 요청데이터를 담는다.
		OrderDetailApiRequest apiRequest =  request.getData();
		
		// 2.업데이트할 데이터를 찾는다.
		Optional<OrderDetail> optional = orderDetailRepo.findById(apiRequest.getId());
		
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
				.map(updateData -> orderDetailRepo.save(updateData))
				.map(updateData -> response(updateData))
				.orElseGet(() -> Header.ERROR("업데이트할 데이터가 없습니다."));
	}

	@Override
	public Header delete(Long id) {
		//1. 삭제할 데이터를 찾는다.
		Optional<OrderDetail> optional =  orderDetailRepo.findById(id);
		
		//2. 데이터를 삭제한다.
		return optional
				.map(orderDetail -> {
					orderDetailRepo.delete(orderDetail);
					return Header.OK();
				})
				.orElseGet(() -> Header.ERROR("삭제할 데이터가 없습니다."));
	}
	
	private Header<OrderDetailApiResponse> response(OrderDetail detail) {
		
		OrderDetailApiResponse body = OrderDetailApiResponse.builder()
					.id(detail.getId())
					.status(detail.getStatus())
					.arrivalDate(detail.getArrivalDate())
					.quantity(detail.getQuantity())
					.totalPrice(detail.getTotalPrice())
					.orderGroupId(detail.getOrderGroup().getId())
					.itemId(detail.getItem().getId())
					.build();
				
		return Header.OK(body);
	}
	
}
