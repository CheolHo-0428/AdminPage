package com.example.study.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.study.model.entity.Item;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.repository.PartnerRepository;

@Service
public class ItemApiLogicService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {
	
	//JpaRepository<Item, Long> baseRepo === ItemRepository itemRepository
	
	@Autowired
	private PartnerRepository partnerRepo;
	
	@Override
	public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
		ItemApiRequest body = request.getData();
		
		Item item = Item.builder()
				.status(body.getStatus())
				.name(body.getName())
				
				.title(body.getTitle())
				.content(body.getContent())
				.price(body.getPrice())
				.brandName(body.getBrandName())
				.registeredAt(LocalDateTime.now())
				.partner(partnerRepo.getOne(body.getPartnerId()))
				.build();
				
		Item newItem = baseRepo.save(item);
		
		ItemApiResponse itemApiResponse = response(newItem); 
		
		return Header.OK(itemApiResponse);
	}

	@Override
	public Header<ItemApiResponse> read(Long id) {
		Optional<Item> optional = baseRepo.findById(id);
		
		return optional
				.map(item -> response(item))
				.map(item -> Header.OK(item))
				.orElseGet(() -> Header.ERROR("데이터가 없습니다."));
			
	}
	
	@Override
	public Header<List<ItemApiResponse>> search(Pageable pageable) {
		Page<Item> items = baseRepo.findAll(pageable);
		
		List<ItemApiResponse> itemList = items.stream()
				.map(item -> response(item))
				.collect(Collectors.toList());
		
		Pagination pagination = Pagination.builder()
				.totalPages(items.getTotalPages())
				.totalElements(items.getTotalElements())
				.currentPage(items.getNumber())
				.currentElements(items.getNumberOfElements())
				.build();
		
		return Header.OK(itemList, pagination);
	}

	@Override
	public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
		
		ItemApiRequest body = request.getData();
		
		Optional<Item> optional = baseRepo.findById(body.getId());
		
		return optional.map(item -> {
			item
				.setStatus(body.getStatus())
				.setName(body.getName())
				.setTitle(body.getTitle())
				.setContent(body.getContent())
				.setPrice(body.getPrice())
				.setBrandName(body.getBrandName())
				.setRegisteredAt(body.getRegisteredAt())
				.setUnregisteredAt(body.getUnregisteredAt());
			return item;
			})
				.map(item -> baseRepo.save(item))
				.map(item -> response(item))
				.map(item -> Header.OK(item))
				.orElseGet(() -> Header.ERROR("데이터가 없습니다."));
	}

	@Override
	public Header delete(Long id) {	
		Optional<Item> optional = baseRepo.findById(id);
		
		return optional
					.map(item -> {
						baseRepo.delete(item);
						return Header.OK();})
					
					.orElseGet(() -> Header.ERROR("삭제할 데이터가 없습니다."));

	}
	
	private ItemApiResponse response(Item item){
		
		ItemApiResponse body = ItemApiResponse.builder()
										.id(item.getId())
										.status(item.getStatus())
										.name(item.getName())
										.title(item.getTitle())
										.content(item.getContent())
										.price(item.getPrice())
										.brandName(item.getBrandName())
										.registeredAt(item.getRegisteredAt())
										.unregisteredAt(item.getUnregisteredAt())
										.partnerId(item.getPartner().getId())
										.build();
		
		
		return body;
	}
}
