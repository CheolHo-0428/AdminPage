package com.example.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.service.BaseService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public abstract class CrudController<Req, Res, Entity> implements CrudInterface<Req, Res> {
	
	@Autowired(required = false)
	protected BaseService<Req, Res, Entity> baseService;
	
	@Override
	@PostMapping("")
	public Header<Res> create(@RequestBody Header<Req> request) {
		log.info("{}", request);
		return baseService.create(request);
	}

	@Override
	@GetMapping("{id}")
	public Header<Res> read(@PathVariable Long id) {
		log.info("read id: {}", id);
		return baseService.read(id);
	}
	
	@Override
	@GetMapping("")
	public Header<List<Res>> search(Pageable pageable) {
		log.info("{}", pageable);
		return baseService.search(pageable);
	}

	@Override
	@PutMapping("")
	public Header<Res> update(@RequestBody Header<Req> request) {
		log.info("{}", request);
		return baseService.update(request);
	}

	@Override
	@DeleteMapping("{id}")
	public Header delete(@PathVariable Long id) {
		log.info("delete id: {}", id);
		return baseService.delete(id);
	}
	
	
}
