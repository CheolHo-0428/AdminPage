package com.example.study.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.service.ItemApiLogicService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/item")
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {
	
	@Autowired
	private ItemApiLogicService service;
	
	@Override
	@PostMapping("")
	public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
		log.info("{}" , request);
		return service.create(request);
	}
	
	@Override
	@GetMapping("{id}")
	public Header<ItemApiResponse> read(@PathVariable Long id) {
		log.info("read id: {}", id);
		return service.read(id);
	}
	
	
	@Override
	@PutMapping("")
	public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
		log.info("{}", request);
		return service.update(request);
	}
	
	
	@Override
	@DeleteMapping("{id}")
	public Header delete(@PathVariable Long id) {
		log.info("delete id: {}", id);
		return service.delete(id);
	}

	
	
}
