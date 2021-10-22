package com.example.study.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Partner;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.PartnerApiRequest;
import com.example.study.model.network.response.PartnerApiResponse;
import com.example.study.repository.CategoryRepository;
import com.example.study.repository.PartnerRepository;

@Service
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {
	
	// JpaRepository<Partner, Long> baseRepo === PartnerRepository partnerRepository
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
		//1.데이터를 요청해서 받는다.
		PartnerApiRequest apiRequest = request.getData();
		
		//2.받은데이터 -> Partner Entity -> Entity 저장
		Partner partner = Partner.builder()
				.name(apiRequest.getName())
				.status(apiRequest.getStatus())
				.address(apiRequest.getAddress())
				.callCenter(apiRequest.getCallCenter())
				.partnerNumber(apiRequest.getPartnerNumber())
				.businessNumber(apiRequest.getBusinessNumber())
				.ceoName(apiRequest.getCeoName())
				.registeredAt(LocalDateTime.now())
				.category(categoryRepo.getOne(apiRequest.getCategoryId()))
				.build();
				
		Partner newPartner = baseRepo.save(partner);
		
		return response(newPartner);
	}

	@Override
	public Header<PartnerApiResponse> read(Long id) {
		//1.데이터 찾기
		Optional<Partner> optional = baseRepo.findById(id);
		
		//2.데이터 응답
		return optional
				.map(partner -> response(partner))
				.orElseGet(() -> Header.ERROR("데이터가 없습니다."));
	}

	@Override
	public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
		//1. 데이터 요청
		PartnerApiRequest apiRequest = request.getData();
		
		//2.업데이트할 데이터 찾기
		Optional<Partner> optional = baseRepo.findById(apiRequest.getId());
		
		return optional
				.map(partner -> {
					partner
						.setId(apiRequest.getId())
						.setName(apiRequest.getName())
						.setStatus(apiRequest.getStatus())
						.setAddress(apiRequest.getAddress())
						.setCallCenter(apiRequest.getCallCenter())
						.setPartnerNumber(apiRequest.getPartnerNumber())
						.setBusinessNumber(apiRequest.getBusinessNumber())
						.setCeoName(apiRequest.getCeoName())
						.setRegisteredAt(apiRequest.getRegisteredAt())
						.setUnregisteredAt(apiRequest.getUnregisteredAt())
						.setCategory(categoryRepo.getOne(apiRequest.getCategoryId()));
					
					return partner;
				})
				.map(updatePartner -> baseRepo.save(updatePartner))
				.map(updatePartner -> response(updatePartner))
				.orElseGet(() -> Header.ERROR("업데이트할 데이터가 없습니다."));	
	}

	@Override
	public Header delete(Long id) {
		//1. 삭제할 데이터 찾기
		Optional<Partner> optional = baseRepo.findById(id);
		
		return optional
				.map(partner -> {
					baseRepo.delete(partner);
					return Header.OK();
				})
				.orElseGet(() -> Header.ERROR("삭제할 데이터가 없습니다."));
	}
	
	private Header<PartnerApiResponse> response(Partner partner){
		
		PartnerApiResponse body = PartnerApiResponse.builder()
					.id(partner.getId())
					.name(partner.getName())
					.status(partner.getStatus())
					.address(partner.getAddress())
					.callCenter(partner.getCallCenter())
					.partnerNumber(partner.getPartnerNumber())
					.businessNumber(partner.getBusinessNumber())
					.ceoName(partner.getCeoName())
					.registeredAt(partner.getRegisteredAt())
					.unregisteredAt(partner.getUnregisteredAt())
					.categoryId(partner.getCategory().getId())
					.build();
		
		return Header.OK(body);
	}

	
}
