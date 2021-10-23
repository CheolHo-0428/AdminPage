package com.example.study.controller.api;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.controller.CrudController;
import com.example.study.model.entity.Partner;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.PartnerApiRequest;
import com.example.study.model.network.response.PartnerApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/partner")
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner> {
	
	@Override
	@GetMapping("")
	public Header<List<PartnerApiResponse>> search(
			@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 10)
			Pageable pageable) {
		
		log.info("{}", pageable);
		return baseService.search(pageable);
	}
	
	
	
	/*
	@Autowired
	private PartnerApiLogicService service;
	
	@PostConstruct
	public void init() {
		this.baseService = service;
	}
	
	
	@Override
	@PostMapping("")
	public Header<PartnerApiResponse> create(@RequestBody Header<PartnerApiRequest> request) {
		log.info("{}", request);
		return service.create(request);
	}

	@Override
	@GetMapping("{id}")
	public Header<PartnerApiResponse> read(@PathVariable Long id) {
		log.info("read id: {}", id);
		return service.read(id);
	}

	@Override
	@PutMapping("")
	public Header<PartnerApiResponse> update(@RequestBody Header<PartnerApiRequest> request) {
		log.info("{}", request);
		return service.update(request);
	}

	@Override
	@DeleteMapping("{id}")
	public Header delete(@PathVariable Long id) {
		log.info("delete id: {}", id);
		return service.delete(id);
	}
	*/
	
}
