package com.example.study.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Item;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.repository.ItemRepositroy;
import com.example.study.repository.PartnerRepository;

@Service
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {
	
	@Autowired
	private ItemRepositroy itemRepo;
	
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
				
		Item newItem = itemRepo.save(item);
		
		return response(newItem);
	}

	@Override
	public Header<ItemApiResponse> read(Long id) {
		Optional<Item> optional = itemRepo.findById(id);
		
		return optional.map(item -> response(item))
				.orElseGet(() -> Header.ERROR("데이터가 없습니다."));
			
	}

	@Override
	public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
		
		ItemApiRequest body = request.getData();
		
		Optional<Item> optional = itemRepo.findById(body.getId());
		
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
				.map(item -> itemRepo.save(item))
				.map(item -> response(item))
				.orElseGet(() -> Header.ERROR("데이터가 없습니다."));
	}

	@Override
	public Header delete(Long id) {	
		Optional<Item> optional = itemRepo.findById(id);
		
		return optional
					.map(item -> {
						itemRepo.delete(item);
						return Header.OK();})
					
					.orElseGet(() -> Header.ERROR("삭제할 데이터가 없습니다."));

	}
	
	private Header<ItemApiResponse> response(Item item){
		
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
		
		
		return Header.OK(body);
	}
}
